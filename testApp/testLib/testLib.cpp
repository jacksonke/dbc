// testLib.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "../includes/testLib.h"
#include <iostream>

using std::cout;
using std::endl;

extern "C" {
    DLL_EXPORT void print() {
        cout << "Success" << endl;

        return;
    }
}


