package fr.thomas.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable

fun PhotoCarrousel(modifier: Modifier=Modifier){

    Row (
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ){
        Image(
            painter = painterResource(id = R.drawable.catane),
            contentDescription = stringResource(R.string.jeux_catane)

        )
        Image(
            painter = painterResource(id = R.drawable.catane),
            contentDescription = stringResource(R.string.jeux_catane)
        )
        Image(
            painter = painterResource(id = R.drawable.catane),
            contentDescription = stringResource(R.string.jeux_catane)
        )
    }

}


@Composable
fun DescriptionCompleteJeu(modifier: Modifier = Modifier){
    Column (modifier=modifier
        .padding(24.dp)
    ){
        Description()
        NombreJoueurs()
        DureeJeu()
        AgeRecommande()
        Disponbilite()
    }

}

@Composable
fun Description(modifier: Modifier=Modifier){
    Row {
        Text(text = "Description")
        Text(text = "Super Jeu")
    }
}

@Composable
fun NombreJoueurs(modifier: Modifier=Modifier){

    Row {
        Text(text = "Nombre de joueurs")
        Text(text = "2-4 joueurs")
    }
}

@Composable
fun DureeJeu(modifier: Modifier=Modifier){

    Row {
        Text(text = "Durée")
        Text(text = "60min")
    }
}


@Composable
fun AgeRecommande(modifier: Modifier=Modifier){

    Row {
        Text(text = "Age Recommandé")
        Text(text = "10 ans")
    }
}

@Composable
fun Disponbilite(modifier: Modifier=Modifier){

    Row {
        Text(text = "Disponibilité")
        Text(text = "Oui")
    }
}


@Composable
fun OneGameScreenApp(){
    Column {
        PhotoCarrousel()
        DescriptionCompleteJeu()
    }
}

@Composable
@Preview
fun OneGameAppPreview(modifier:Modifier = Modifier){
    Surface(
        modifier=modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){

    }
    OneGameScreenApp()
}