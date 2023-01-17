#include "main.h"

int braceFlag = 0;

// Функция возвращает 1 в случае, если начало строки source совпадает с pattern
int compare(char *source, char *pattern) {
    int res = 0;
    for (; *pattern && *source == *pattern; source++, pattern++) {
    }
    if (*pattern == 0) res++;
    return res;
}

// Функция пушит в стек операцию и вытаскивает их из стека при надобности
// возвращает 1 в случае ошибки со скобками
int pushOperation(int code, int priority, Stack **head, Stack **headResult) {
    int ret = 0;
    if (code != '(' && code != ')') {
        if (priority == 4 && (*head) && (*head)->priority == 4) {
            push(head, 10, 4);
        } else {
            while (*head != NULL && (*head)->priority >= priority && (*head)->opcode != '(') {
                int prevpr = (*head)->priority;
                int prevop = pop(head);
                pushToTail(headResult, prevop, prevpr);
            }
            if (*head == NULL || (*head)->priority < priority || (*head)->opcode == '(') {
                push(head, code, priority);
            }
        }
    } else if (code == '(') {
        push(head, code, priority);
        braceFlag++;
    } else if (code == ')') {
        while (*head != NULL && (*head)->opcode != '(') {
            int prevpr = (*head)->priority;
            int prevop = pop(head);
            pushToTail(headResult, prevop, prevpr);
        }
        if (*head == NULL) {
            ret = 1;
        } else {
            pop(head);
            braceFlag--;
        }
    }
    return ret;
}

void codeToString(char *str, int code) {
    if (code >= 40 && code <= 94) {
        str[0] = (char)code;
        str[1] = 0;
    } else {
        switch (code) {
            case 10:
                snprintf(str, N, "%s", "-u");
                break;
            case 1:
                snprintf(str, N, "%s", "sin");
                break;
            case 2:
                snprintf(str, N, "%s", "cos");
                break;
            case 3:
                snprintf(str, N, "%s", "tan");
                break;
            case 4:
                snprintf(str, N, "%s", "atan");
                break;
            case 5:
                snprintf(str, N, "%s", "sqrt");
                break;
            case 6:
                snprintf(str, N, "%s", "ln");
                break;
            case 7:
                snprintf(str, N, "%s", "asin");
                break;
            case 8:
                snprintf(str, N, "%s", "acos");
                break;
            case 9:
                snprintf(str, N, "%s", "log");
                break;
            case 11:
                snprintf(str, N, "%s", "mod");
                break;
        }
    }
}

int stackCalculate(Stack **head, double X, double *Y) {
    int ret = 0;
    Stacknum *numbers;
    initnum(&numbers);
    while (!ret && *head) {
        double code;
        if ((*head)->priority == 0) {
            code = pop(head);
            pushnum(&numbers, code);
        } else if ((*head)->priority == -1) {
            code = pop(head);
            pushnum(&numbers, X);
        } else {
            code = pop(head);
            if (code > 10) {
                ret = makeBinaryOperation(&numbers, code);
            } else {
                ret = makeUnaryOperation(&numbers, code);
            }
        }
    }
    if (numbers && numbers->next != NULL) ret = 3;
    if (!ret) *Y = popnum(&numbers);
    destroynum(&numbers);
    return ret;
}

int makeBinaryOperation(Stacknum **numbers, int code) {
    int ret = 0;
    double first, second;
    if ((*numbers) != NULL)
        second = popnum(numbers);
    else
        ret = 3;
    if ((*numbers) != NULL)
        first = popnum(numbers);
    else
        ret = 3;
    if (!ret) {
        switch (code) {
            case '+':
                pushnum(numbers, first + second);
                break;
            case '-':
                pushnum(numbers, first - second);
                break;
            case '*':
                pushnum(numbers, first * second);
                break;
            case '/':
                if (fabs(second) > EPS)
                    pushnum(numbers, first / second);
                else
                    ret = 4;
                break;
            case '^':
                pushnum(numbers, pow(first, second));
                break;
            case 11:
                if (fabs(second) > EPS)
                    pushnum(numbers, fmod(first, second));
                else
                    ret = 10;
                break;
        }
    }
    return ret;
}

double forceToZero(double x) { return (fabs(x) <= EPS) ? 0 : x; }

double forceToInf(double x) { return (x > 1e15) ? INFINITY : ((x < -1e15) ? -INFINITY : x); }

int makeUnaryOperation(Stacknum **numbers, int code) {
    int ret = 0;
    double first;
    if ((*numbers) != NULL)
        first = popnum(numbers);
    else
        ret = 3;
    if (!ret) {
        switch (code) {
            case 10:
                pushnum(numbers, -first != 0.0 ? -first : 0);
                break;
            case 1:
                pushnum(numbers, forceToZero(sin(first)));
                break;
            case 2:
                pushnum(numbers, forceToZero(cos(first)));
                break;
            case 3:
                pushnum(numbers, forceToInf(forceToZero(tan(first))));
                break;
            case 4:
                pushnum(numbers, forceToZero(atan(first)));
                break;
            case 5:
                if (first > -EPS)
                    pushnum(numbers, sqrt(fabs(first)));
                else
                    ret = 5;
                break;
            case 6:
                if (first > -EPS)
                    pushnum(numbers, forceToZero(log(fabs(first))));
                else
                    ret = 6;
                break;
            case 7:
                if (first >= -1 && first <= 1)
                    pushnum(numbers, forceToZero(asin(first)));
                else
                    ret = 7;
                break;
            case 8:
                if (first >= -1 && first <= 1)
                    pushnum(numbers, forceToZero(acos(first)));
                else
                    ret = 8;
                break;
            case 9:
                if (first > -EPS)
                    pushnum(numbers, forceToZero(log10(fabs(first))));
                else
                    ret = 8;
                break;
        }
    }
    return ret;
}
