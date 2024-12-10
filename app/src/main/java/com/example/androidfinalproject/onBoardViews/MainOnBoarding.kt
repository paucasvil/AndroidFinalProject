package com.example.androidfinalproject.onBoardViews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.data.PageData
import com.example.androidfinalproject.dataStore.StoreBoarding

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class,ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController,store: StoreBoarding){
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.mano,
            "Bienvenid@",
            "Bienvenid@ a VolleyScore "
        )
    )
    items.add(
        PageData(
            R.raw.volleyball_hitdown,
            "Partidos",
            "Aqui podrás registar los puntos de tus partidas de voleibal!"
        )
    )
    items.add(
        PageData(
            R.raw.volleyball_animation,
            "Equipos",
            "Podrás crear los equipos que tu desees!"
        )
    )

    val pagerState = rememberPagerState (
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items, pagerState = pagerState, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White), navController,store
    )
}