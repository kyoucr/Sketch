/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.spear.util;

import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;

public class ImageProcessUtils {

    /**
     * 计算影射区域
     * @param sourceSize 原尺寸
     * @param targetSize 目标尺寸
     * @param scaleType 显示方式
     * @return 影射区域
     */
    public static Rect computeSrcRect(Point sourceSize, Point targetSize, ImageView.ScaleType scaleType){
        if(scaleType == ImageView.ScaleType.CENTER_INSIDE || scaleType == ImageView.ScaleType.MATRIX || scaleType == ImageView.ScaleType.FIT_XY){
            return new Rect(0, 0, sourceSize.x, sourceSize.y);
        }else{
            float scale;
            if(Math.abs(sourceSize.x - targetSize.x) < Math.abs(sourceSize.y - targetSize.y)){
                scale = (float) sourceSize.x/targetSize.x;
                if((int)(targetSize.y*scale) > sourceSize.y){
                    scale = (float) sourceSize.y/targetSize.y;
                }
            }else{
                scale = (float) sourceSize.y/targetSize.y;
                if((int)(targetSize.x*scale) > sourceSize.x){
                    scale = (float) sourceSize.x/targetSize.x;
                }
            }
            int srcLeft;
            int srcTop;
            int srcWidth = (int)(targetSize.x*scale);
            int srcHeight = (int)(targetSize.y*scale);
            if (scaleType == ImageView.ScaleType.FIT_START) {
                srcLeft = 0;
                srcTop = 0;
            } else if (scaleType == ImageView.ScaleType.FIT_END) {
                if(sourceSize.x > sourceSize.y){
                    srcLeft = sourceSize.x - srcWidth;
                    srcTop = 0;
                }else{
                    srcLeft = 0;
                    srcTop = sourceSize.y - srcHeight;
                }
            } else {
                if(sourceSize.x > sourceSize.y){
                    srcLeft = (sourceSize.x - srcWidth)/2;
                    srcTop = 0;
                }else{
                    srcLeft = 0;
                    srcTop = (sourceSize.y - srcHeight)/2;
                }
            }
            return new Rect(srcLeft, srcTop, srcLeft+srcWidth, srcTop+srcHeight);
        }
    }
}