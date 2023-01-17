#ifndef SRC_STACK_H_
#define SRC_STACK_H_

typedef struct stack {
    double opcode;
    int priority;
    struct stack *next;
} Stack;

typedef struct stacknum {
    double num;
    struct stacknum *next;
} Stacknum;

void init(Stack **head);
void initnum(Stacknum **head);
void push(Stack **head, double operation, int priority);
void pushnum(Stacknum **head, double num);
void pushToTail(Stack **head, double operation, int priority);
double pop(Stack **head);
double popnum(Stacknum **head);
void destroy(Stack **head);
void destroynum(Stacknum **head);
void copyStack(Stack **stackDest, Stack *stackSrc);

#endif  // SRC_STACK_H_
