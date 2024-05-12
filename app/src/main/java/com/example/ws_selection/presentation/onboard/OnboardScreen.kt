@file:OptIn(ExperimentalFoundationApi::class)

package com.example.ws_selection.presentation.onboard

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ws_selection.presentation.common.CustomPrimaryButton
import com.example.ws_selection.presentation.onboard.components.CustomPagerIndicator
import com.example.ws_selection.presentation.onboard.components.PagerScreen
import com.example.ws_selection.presentation.onboard.components.StartOnboardScreen
import com.example.ws_selection.presentation.ui.theme.background
import kotlinx.coroutines.launch


@Composable
fun OnboardScreen(

) {

    val pagerList = listOf(
        OnboardPage.Second,
        OnboardPage.Third
    )
    val state = rememberPagerState(){
        3
    }

    Column(
        Modifier.background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box{
            HorizontalPager(
                state = state
            ) { page ->
                if (page == 0){
                    StartOnboardScreen{
                    }
                }else{
                    PagerScreen(
                        page = pagerList[page-1],
                        pageNumber = page
                    )
                }
            }
            if (state.currentPage == 0){
                CustomPagerIndicator(
                    selectedPage = state.currentPage,
                    count = 3,
                    modifier = Modifier
                        .padding(bottom = 145.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }

        if (state.currentPage != 0){
            Spacer(modifier = Modifier.height(40.dp))
            CustomPagerIndicator(
                selectedPage = state.currentPage,
                count = 3,
                modifier = Modifier
            )
        }
        
        val scope = rememberCoroutineScope()
        Spacer(modifier = Modifier.weight(1f))
        CustomPrimaryButton(
            title = "Начать",
            onClickListener = {
                scope.launch {
                    state.animateScrollToPage(
                        state.currentPage+1,
                        animationSpec = tween(1200)
                    )
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
    }
}