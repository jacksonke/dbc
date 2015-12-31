#ifndef __TEST_LIB_H__
#define __TEST_LIB_H__

#ifdef BUILD_DLL
    #define DLL_EXPORT __declspec(dllexport)
#else
    #define DLL_EXPORT __declspec(dllimport)
#endif

extern "C" {
    DLL_EXPORT void print();
}
#endif