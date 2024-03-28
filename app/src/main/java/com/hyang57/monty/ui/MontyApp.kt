package com.hyang57.monty.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hyang57.monty.ui.screens.GameDestination
import com.hyang57.monty.ui.screens.HomeDestination
import com.hyang57.monty.ui.screens.HomeScreen
import com.hyang57.monty.ui.screens.SettingDestination
import com.hyang57.monty.ui.screens.StatsDestination
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hyang57.monty.ui.screens.GameScreen

interface NavDestination {
    val route: String
    val titleId: Int
}

@Composable
fun MontyNavHost(
    viewModel: MontyViewModel,
    gameState: GameState,
    modifier: Modifier = Modifier,
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ){
        composable(
            route = HomeDestination.route
        ) {
            HomeScreen(

                navToGameScreen = {
                    viewModel.newRound()
                    navController.navigate(GameDestination.route)
                },
                navToSettingScreen = {
                    navController.navigate(SettingDestination.route)
                },
                navToStatsScreen = {
                    navController.navigate(StatsDestination.route)
                },
            )}
            composable(
                route = GameDestination.route
            ) {
                //viewModel.newRound()
                //Log.i("check","newround")
                GameScreen(
                    gameState = gameState,
                    onSelect = {
                        viewModel.selectCard(selection = it.id)
                        Log.i("viewModel.selectCard: ","${it.id}")
                        Log.i("onSelect: ","${it.isFlipped}")
                            },
                    onHome = {
                        navController.navigate(HomeDestination.route)
                    },
                    onStats = {
                        navController.navigate(StatsDestination.route)
                    }
                        )
                    }


            }

    }


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MontyApp(
    navController: NavHostController = rememberNavController(),
    viewModel: MontyViewModel,
    gameState: GameState
) {
    MontyNavHost(
        gameState = gameState,
        viewModel = viewModel(),
        navController = navController
    )
}