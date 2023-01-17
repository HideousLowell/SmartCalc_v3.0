#include "main.h"

int parsing(char *expression, Stack **head, Stack **headResult) {
    char *p = expression;
    int interrupt = 0;
    while (*p != '\n' && *p && !interrupt) {
        // Если считывается число
        char *next;
        if (*p >= '0' && *p <= '9') {
            pushToTail(headResult, strtod(p, &next), 0);
            p = next;
        } else if (*p == 'x') {
            pushToTail(headResult, 'x', -1);
            p++;
        } else if (compare(p, "pi")) {
            pushToTail(headResult, M_PI, 0);
            p += 2;
        } else if (*p == 'e') {
            pushToTail(headResult, M_E, 0);
            p++;
        } else {
            int op, pr;
            if ((op = getBinaryOperationCode(&p, &pr, expression)) != -1) {
                if (!pushOperation(op, pr, head, headResult))
                    p++;
                else
                    interrupt = 1;
            } else if ((op = getUnaryOperationCode(&p, &pr)) != -1) {
                if (!(pr != -2 ? !pushOperation(op, pr, head, headResult) : 1)) {
                    interrupt = 1;
                }
            } else {
                interrupt = 2;
            }
        }
    }
    while (*head != NULL && !interrupt) {
        int prevpr = (*head)->priority;
        double prevop = pop(head);
        pushToTail(headResult, prevop, prevpr);
    }
    return interrupt;
}

#define binaryCheck(str, initstr)                                                              \
    ((*str != initstr) && ((*(*str - 1) >= '0' && *(*str - 1) <= '9') || *(*str - 1) == ')' || \
                           *(*str - 1) == 'x' || *(*str - 1) == 'i' || *(*str - 1) == 'e'))

int getBinaryOperationCode(char **str, int *pr, const char *initstr) {
    int code = -1;
    if (**str == '+') {
        if (binaryCheck(str, initstr)) {
            code = '+';
            *pr = 1;
        }
    } else if (**str == '*' || **str == '/' || compare(*str, "mod")) {
        code = (**str == '*' || **str == '/') ? **str : 11;
        if (code == 11) (*str) += 2;
        *pr = 2;
    } else if (**str == '^') {
        code = '^';
        *pr = 3;
    } else if (**str == '-') {
        if (binaryCheck(str, initstr)) {
            code = '-';
            *pr = 1;
        }
    } else if (**str == '(' || **str == ')') {
        code = **str;
        *pr = 10;
    }
    return code;
}

int getUnaryOperationCode(char **str, int *pr) {
    int code = -1;
    if (**str == '-') {
        code = 10;
        *pr = 4;
        (*str)++;
    } else if (**str == '+') {
        code = 0;
        *pr = -2;
        (*str)++;
    } else if (compare(*str, "sin")) {
        code = 1;
        *pr = 5;
        (*str) += 3;
    } else if (compare(*str, "cos")) {
        code = 2;
        *pr = 5;
        (*str) += 3;
    } else if (compare(*str, "tan")) {
        code = 3;
        *pr = 5;
        (*str) += 3;
    } else if (compare(*str, "atan")) {
        code = 4;
        *pr = 5;
        (*str) += 4;
    } else if (compare(*str, "sqrt")) {
        code = 5;
        *pr = 5;
        (*str) += 4;
    } else if (compare(*str, "ln")) {
        code = 6;
        *pr = 5;
        (*str) += 2;
    } else if (compare(*str, "asin")) {
        code = 7;
        *pr = 5;
        (*str) += 4;
    } else if (compare(*str, "acos")) {
        code = 8;
        *pr = 5;
        (*str) += 4;
    } else if (compare(*str, "log")) {
        code = 9;
        *pr = 5;
        (*str) += 3;
    }
    return code;
}
