//
//  main.cpp
//  eyetracking
//
//  Created by 김민정 on 2020/04/08.
//  Copyright © 2020 김민정. All rights reserved.
//

#include <cstdio>
#include <string>
#include <iostream>
#include <fstream>

#include "findEyeCorner.h"
#include "findEyeCenter.h"
#include "constants.h"
#include "helpers.h"
#include "logEyeTracking.h"
#include "eyeLike.h"
//#include "curlSend.h"

#define INF 1234567890

using namespace std;
int boundary[8];

struct eyeBoundary{
        
    int left_minX;
    int left_minY;
    
    int right_minX;
    int right_minY;
    
    int left_maxX;
    int left_maxY;
    
    int right_maxX;
    int right_maxY;
}
int main(void)
{
    printf("hello world!\n");

    int total=0;
    
    CvCapture *capture;
    eyeLocation eyes;

    capture = eyeLikeInit();
    eyes = eyeLikeModule(capture);
    
    eyeBoundary bound = {-INF,-INF, -INF, -INF, INF, INF, INF, INF};

    bound = updateBoundary(eyes,bound);
    
    ofstream fout;
    fout.open("init.ini");
    if(fout.is_open()){
        //파일이 있으면
        total++;
        eyeChecking(bound, eyes);
        
    }else{
        //파일 없으면
        
    }
    eyeLikeEnd();

    return 0;
}


eyeBoundary updateBoundary( eyeLocation eyes ,eyeBoundary bound ){
    //파일에 각각의 경계값 저장
    cout << "각 모서리를 2초씩 봐주세요 :)\n" ;
    
    bound.left_minX = min(eyes.leftCenter.x ,bound.left_minX );
    bound.left_minY = min(eyes.leftCenter.y, bound.left_minY );
    
    bound.right_minX = min(eyes.rightCenter.x, bound.right_minX);
    bound.right_minY = min(eyex.rightCenter.y, bound.right_minY);
    
    bound.left_maxX = max(eyes.leftCenter.x, bound.left_maxX);
    bound.left_maxY = max(eyes.leftCenter.y, bound.left_maxY);
    
    bound.right_maxX = max(eyes.rightCenter.x, bound.right_maxX);
    bound.right_maxY = max(eyes.rightCenter.y, bound.right_maxY);
    
    return bound;
    
}
void writeBoundary(eyeBoundary bound){

    ofstream fout;
    fout.open("init.ini");
    fout<< bound.left_minX <<' '<< bound.left_minY<<' '<< bound.right_minX <<' '<< bound.right_minY<<' '<< bound.left_maxX <<' '<< bound.left_maxY <<' '<< bound.right_maxX <<' '<< bound.right_maxY;
    
    fout.close();
    
}

int eyeChecking(eyeBoundary bound, eyeLocation eyes){
    //init.txt파일 열어서 경계값기준으로 집중도 계산
    ifstream fin;
    fin.open("inin.ini");
    fin >> boundary[0] >> boundary[1] >> boundary[2] >> boundary[3] >>  boundary[4] >> boundary[5] >> boundary[6] >> boundary[7] ;
    fin.close();

    if( eyes.leftCenter.x > boundary[0] || eyes.leftCenter.y < boundary[1] || eyes.rightCenter.x > boundary[2] || eyes.rightCenter.y > boundary[3] || eyes.leftCenter.x < boundary[4] || eyes.leftCenter.y < boundary[5] || eyes.rightCenter.x < boundary[6] || eyes.rightCenter.y <boundary[7]){
        return 1;
    }else{
        return 0;
    }
    
}
void concentration( int eyeChecking, int total){

    
}

