#include "main.h"

extern int braceFlag;

char* message(int ret) {
    if (ret == 2) {
        return "Unrecognized symbol!";
    } else if (ret == 1 || braceFlag) {
        return "Check for the missing brackets!";
    } else if (ret == 3) {
        return "Check expression for valid data!";
    } else if (ret == 4) {
        return "Divide by zero is forbidden!";
    } else if (ret == 5) {
        return "Can't take sqrt() from negative values!";
    } else if (ret == 6) {
        return "Can't take ln() from negative values!";
    } else if (ret == 7) {
        return "Argument for asin() is out of range [-1, 1]!";
    } else if (ret == 8) {
        return "Argument for acos() is out of range [-1, 1]!";
    } else if (ret == 9) {
        return "Can't take log() from negative values!";
    } else if (ret == 10) {
        return "Second argument of mod is zero!";
    } else {
        return "";
    }
}

char *getResultString(char *expression) {
    Stack *head, *headResult;
    init(&head);
    init(&headResult);
    char *resstr = (char *)calloc(64, 1);
    int ret = parsing(expression, &head, &headResult);
    if (!ret && !braceFlag) {
        double res;
        ret = stackCalculate(&headResult, 0, &res);
        if (!ret) {
            snprintf(resstr, (size_t)64, "%.10g", res);
        } else {
            return message(ret);
        }
    }
    if (head) destroy(&head);
    if (headResult) destroy(&headResult);
    return resstr;
}
