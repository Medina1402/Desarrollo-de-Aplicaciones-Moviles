package com.uabc.amc.cinemareview.utils

class MathScreen {
    companion object {
        const val WidthGrid = 600

        fun dpScreenGridAdapter(pixels: Int, dpi: Float, widthGrid: Int): Int {
            var dpGrids = (pixels / dpi) / widthGrid
            dpGrids -= (dpGrids%1)
            if(dpGrids < 1) return 1
            return dpGrids.toInt()
        }
    }
}