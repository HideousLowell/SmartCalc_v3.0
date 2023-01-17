#include <stdlib.h>
#include "stack.h"

void init(Stack **head) {
    *head = NULL;
}

void initnum(Stacknum **head) {
    *head = NULL;
}

void push(Stack **head, double operation, int priority) {
    Stack *temp = (Stack *)malloc(sizeof(Stack));
    temp->opcode = operation;
    temp->priority = priority;
    temp->next = *head;
    *head = temp;
}

void pushnum(Stacknum **head, double num) {
    Stacknum *temp = (Stacknum *)malloc(sizeof(Stacknum));
    temp->num = num;
    temp->next = *head;
    *head = temp;
}

void pushToTail(Stack **head, double operation, int priority) {
    if (*head == NULL) {
        push(head, operation, priority);
    } else {
        Stack *temphead = *head;
        while ((*head)->next != NULL) {
            *head = (*head)->next;
        }
        Stack *temp = (Stack *)malloc(sizeof(Stack));
        temp->opcode = operation;
        temp->priority = priority;
        temp->next = NULL;
        (*head)->next = temp;
        *head = temphead;
    }
}

double pop(Stack **head) {
    double ret = -1;
    if (*head != NULL) {
        Stack *prev = *head;
        ret = prev->opcode;
        *head = (*head)->next;
        free(prev);
    }
    return ret;
}

double popnum(Stacknum **head) {
    double ret = 0;
    if (*head != NULL) {
        Stacknum *prev = *head;
        ret = prev->num;
        *head = (*head)->next;
        free(prev);
    }
    return ret;
}

void destroy(Stack **head) {
    if (*head != NULL) {
        while ((*head)->next) {
            Stack *prev = *head;
            *head = (*head)->next;
            free(prev);
        }
        free(*head);
    }
}

void destroynum(Stacknum **head) {
    if (*head != NULL) {
        while ((*head)->next) {
            Stacknum *prev = *head;
            *head = (*head)->next;
            free(prev);
        }
        free(*head);
    }
}

void copyStack(Stack **stackDest, Stack *stackSrc) {
    while (stackSrc) {
        pushToTail(stackDest, stackSrc->opcode, stackSrc->priority);
        stackSrc = stackSrc->next;
    }
}
