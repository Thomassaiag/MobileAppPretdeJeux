package fr.thomas.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun DescriptionCompleteJeuTitres(modifier: Modifier = Modifier){
    Column (modifier=modifier)
    {
        Text(text = "Description")
        Text(text = "Nombre de joueurs")
        Text(text = "Durée")
        Text(text = "Age Recommandé")
        Text(text = "Disponibilité")
    }

}

@Composable
fun DescriptionCompleteJeuValeurs(modifier: Modifier=Modifier){
    Column (modifier=modifier
        .padding(start = 12.dp)
    ){
        Text(text = "Super Jeu")
        Text(text = "2-4 joueurs")
        Text(text = "60min")
        Text(text = "10 ans")
        Text(text = "Oui")
    }

}

@Composable
fun ToutesInfosJeux(modifier: Modifier=Modifier){
    Row(modifier=modifier
        .padding(24.dp)
    ) {
        DescriptionCompleteJeuTitres()
        DescriptionCompleteJeuValeurs()
    }

}

@Composable
fun OneGameScreenApp(modifier: Modifier=Modifier){

    Column (modifier=modifier,
        horizontalAlignment=Alignment.CenterHorizontally
        ){
        PhotoCarrousel()
        ToutesInfosJeux()
        BottomBar()
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