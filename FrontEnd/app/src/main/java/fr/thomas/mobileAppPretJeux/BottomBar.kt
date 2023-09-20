package fr.thomas.mobileAppPretJeux

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(modifier: Modifier = Modifier){
    Row (modifier= modifier
        .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_action_home),
            contentDescription = "Home Button"
        )

        Image(
            painter = painterResource(id = R.drawable.ic_action_profil),
            contentDescription = "Profil Button"
        )

        Image(
            painter = painterResource(id = R.drawable.ic_action_calendar),
            contentDescription = "Calendar Button"
        )
    }
}


@Composable
@Preview

fun BottomBarPreview(){
    BottomBar()
}
