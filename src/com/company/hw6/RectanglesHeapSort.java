package com.company.hw6;

import com.company.hw6.UtilityClasses.Rectangle;
import com.company.hw6.UtilityClasses.RectangleComparator;

public class RectanglesHeapSort {
    public static void inPlaceHeapSort(Rectangle[] rectangles) {
        RectangleComparator comp = new RectangleComparator();
        for(int i = 0; i < rectangles.length; i++) {
            if(comp.compare(rectangles[i], rectangles[(i - 1) / 2]) > 0)  {
                Rectangle temp = rectangles[i];
                rectangles[i] = rectangles[(i - 1) / 2];
                rectangles[(i - 1) / 2] = temp;
            }
        }

        for (int i = rectangles.length - 1; i > 0; i--) {
            Rectangle temp1 = rectangles[0];
            rectangles[0] = rectangles[i];
            rectangles[i] = temp1;
            int index = 0;
            while (index < i) {
                Rectangle temp2 = rectangles[index];
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                if(left >= i && right >= i) break;
                if((left < i && right >= i) || comp.compare(rectangles[left], rectangles[right]) > 0) {
                    if(comp.compare(rectangles[index], rectangles[left]) < 0) {
                        rectangles[index] = rectangles[left];
                        rectangles[left] = temp2;
                        index = left;
                    } else break;
                } else if((right < i && left >= i) || comp.compare(rectangles[left], rectangles[right]) <= 0){
                    if(comp.compare(rectangles[index], rectangles[right]) < 0) {
                        rectangles[index] = rectangles[right];
                        rectangles[right] = temp2;
                        index = right;
                    }
                    else break;
                }
            }
        }
    }
}



