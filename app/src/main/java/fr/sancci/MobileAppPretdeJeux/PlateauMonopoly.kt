package fr.sancci.MobileAppPretdeJeux

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val colors = listOf(
    Color(0xFF9C183F), //0
    Color(0xFF0072BB), // 1
    Color(0xFF8DCF5E), // 2
    Color(0xFFF9CE30), // 3
    Color(0xFF532F5B), // 4
    Color(0xFFF15A29),  //5
    Color(0xFFFF69B4),//6
    Color(0xFF00FFFF),//7
    Color(0xFFFFA500)//8
)

   @Composable
    fun Plateau() {
        TopHorizontalLine()
        BottomHorizontalLine()
        LeftVerticalLine()
        RightVerticalLine()
    }

  /* @Composable
fun Plateau() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopHorizontalLine()
        //Spacer(modifier = Modifier.weight(1f))
        BottomHorizontalLine()
        LeftVerticalLine()
        RightVerticalLine()
    }
}*/


    @Composable
    fun DividedCase(
        largeur: Dp,
        hauteur: Dp,
        couleur: Color = Color.Transparent,

    ) {
        Column(
            modifier = Modifier
                .width(largeur)
                .height(hauteur)
        ) {
            // Partie transparente en haut
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(1.dp, Color.Black)
                    .background(Color.Transparent)
            )

            // Partie colorée en bas avec la couleur spécifiée
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(13.dp)
                    .border(1.dp, Color.Black)
                    .background(couleur)
            )
        }
    }
@Composable
fun HorizontalDividedCase(
    largeur: Dp,
    hauteur: Dp,
    couleur: Color = Color.Transparent,

    ) {
    Row(
        modifier = Modifier
            .width(largeur)
            .height(hauteur)
    ) {
        // Partie transparente en haut
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .border(1.dp, Color.Black)
                .background(Color.Transparent)
        )

        // Partie colorée en bas avec la couleur spécifiée
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(13.dp)
                .border(1.dp, Color.Black)
                .background(couleur)
        )
    }
}

    @Composable
    fun Corner(
        largeur: Dp,
        hauteur: Dp
    ) {
        Box(
            modifier = Modifier
                .width(largeur)
                .height(hauteur)
                .border(1.dp, Color.Black)
        )
    }

    @Composable
    fun EmptyCase(
        largeur: Dp,
        hauteur: Dp
    ) {
        Box(
            modifier = Modifier
                .width(largeur)
                .height(hauteur)
                .border(1.dp, Color.Black)
        )
    }

@Composable
fun TopHorizontalLine() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    //horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Corner(53.dp, 53.dp)
                    DividedCase(33.dp, 53.dp, colors[0])
                    EmptyCase(33.dp, 53.dp)
                    DividedCase(33.dp, 53.dp, colors[0])
                    DividedCase(33.dp, 53.dp, colors[0])
                    EmptyCase(33.dp, 53.dp)
                    DividedCase(33.dp, 53.dp, colors[3])
                    DividedCase(33.dp, 53.dp, colors[3])
                    EmptyCase(33.dp, 53.dp)
                    DividedCase(33.dp, 53.dp, colors[3])
                    Corner(53.dp, 53.dp)
                }
            }
        }
    )
}
@Composable
fun BottomHorizontalLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .width(403.dp)
                .height(403.dp)
                .then(Modifier.rotate(180f)),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ){
            //Spacer(modifier = Modifier.width(60.dp))
            Corner(53.dp, 53.dp)
            DividedCase(33.dp, 53.dp, colors[4])
            EmptyCase(33.dp, 53.dp)
            DividedCase(33.dp, 53.dp, colors[4])
            EmptyCase(33.dp, 53.dp)
            EmptyCase(33.dp, 53.dp)
            DividedCase(33.dp, 53.dp, colors[7])
            EmptyCase(33.dp, 53.dp)
            DividedCase(33.dp, 53.dp, colors[7])
            DividedCase(33.dp, 53.dp, colors[7])
           Corner(53.dp, 53.dp)

        }
    }
}



@Composable
fun LeftVerticalLine(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(), // Ajoutez ceci pour occuper toute la hauteur
        ){
                    Corner(53.dp, 53.dp)
                    HorizontalDividedCase(53.dp, 33.dp,colors[8])
                    HorizontalDividedCase(53.dp, 33.dp,colors[8])
                    EmptyCase(53.dp, 33.dp)
                    HorizontalDividedCase(53.dp, 33.dp,colors[8])
                    EmptyCase(53.dp, 33.dp)
                    HorizontalDividedCase(53.dp, 33.dp,colors[6])
                    HorizontalDividedCase(53.dp, 33.dp,colors[6])
                    EmptyCase(53.dp, 33.dp)
                    HorizontalDividedCase(53.dp, 33.dp,colors[6])
                    Corner(53.dp, 53.dp)
                }
            }

@Composable
fun RightVerticalLine(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight() // Ajoutez ceci pour occuper toute la hauteur

    ) {
        Column(
            modifier = Modifier
                .height(403.dp)
                .width(404.dp)
                .then(Modifier.rotate(180f)),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ){
            //Spacer(modifier = Modifier.height(368.dp)) // Ajoutez un espace pour déplacer la ligne vers le haut
            Corner(53.dp, 53.dp)
            HorizontalDividedCase(53.dp, 33.dp, colors[1])
            HorizontalDividedCase(53.dp, 33.dp, colors[1])
            EmptyCase(53.dp, 33.dp)
            HorizontalDividedCase(53.dp, 33.dp, colors[1])
            EmptyCase(53.dp, 33.dp)
            EmptyCase(53.dp, 33.dp)
            HorizontalDividedCase(53.dp, 33.dp, colors[2])
            EmptyCase(53.dp, 33.dp)
            HorizontalDividedCase(53.dp, 33.dp, colors[2])
            Corner(53.dp, 53.dp)
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun PlateauPreview(){
    Plateau()
}*/
