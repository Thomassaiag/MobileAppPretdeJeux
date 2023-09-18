package fr.thomas.mobileAppPretJeux

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun FakeScreen(modifier: Modifier =Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "profile") {
        composable("profile") { Profile(/*...*/) }
        composable("friendslist") { FriendsList(/*...*/) }
        /*...*/
    }
}

@Composable
fun Profile(){
    Text(text="Profil text")
}


@Composable
fun FriendsList(){
    Text(text="Friend list text")
}

@Composable
@Preview
fun FakeScreenPreview(){
    FakeScreen()
}