#ifndef MAIN_H_
#define MAIN_H_

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.h"

#define N 255
#define EPS 1e-15
char *getResultString(char *expression);

int parsing(char *expression, Stack **head, Stack **headResult);
int getBinaryOperationCode(char **str, int *pr, const char *initstr);
int getUnaryOperationCode(char **str, int *pr);
int compare(char *source, char *pattern);
int pushOperation(int code, int priority, Stack **head, Stack **headResult);
void codeToString(char *str, int code);
void printStack(Stack *head);
int stackCalculate(Stack **head, double X, double *Y);
int makeBinaryOperation(Stacknum **numbers, int code);
int makeUnaryOperation(Stacknum **numbers, int code);

#endif // MAIN_H_