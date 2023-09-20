package fr.thomas.mobileAppPretJeux

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun PhotoCarrousel(modifier: Modifier=Modifier){

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier=Modifier
            .fillMaxWidth()
    ){
        items (10) { i->
            Image(
                painter = painterResource(id = R.drawable.catane),
                contentDescription = stringResource(R.string.jeux_catane)

            )

        }
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
    Column (
        horizontalAlignment=Alignment.End,
        modifier=modifier
            .padding(start = 120.dp)
    ){
        Text(text = "Super Jeu",
        color = colorResource(id = R.color.monopoly_maroon)
        )
        Text(text = "2-4 joueurs",color = colorResource(id = R.color.monopoly_maroon))
        Text(text = "60min",color = colorResource(id = R.color.monopoly_maroon))
        Text(text = "10 ans",color = colorResource(id = R.color.monopoly_maroon))
        Text(text = "Oui",color = colorResource(id = R.color.monopoly_maroon))
    }

}

@Composable
fun ToutesInfosJeux(modifier: Modifier=Modifier){
    Row(modifier= modifier
        .padding(24.dp)
        .fillMaxWidth()
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