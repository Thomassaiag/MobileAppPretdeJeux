package fr.thomas.mobileAppPretJeux



import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.thomas.mobileAppPretJeux.ui.theme.TestEcranJeuxTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import fr.thomas.mobileAppPretJeux.classes.Category
import fr.thomas.mobileAppPretJeux.classes.NavigationBottomItem
import fr.thomas.mobileAppPretJeux.classes.NewsFeed



//@file:OptIn(ExperimentalMaterial3Api::class)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestEcranJeuxTheme {
                // Utilisez Scaffold pour créer la mise en page principale de votre application
                Scaffold(
                    topBar = {
                        //Ajoutez ici votre BottomBar ou NavigationBarSample
                        TopAppBarSample()
                    },
                    content = {
                        GamesScreen()
                    },
                    bottomBar = {
                        //Ajoutez ici votre BottomBar ou NavigationBarSample
                        NavigationBarSample()
                    },
                )

            }
        }
    }
}



@ExperimentalMaterial3Api
@Composable
fun TopAppBarSample(){
    // État de la connexion de l'utilisateur (pour le bouton de déco/reco)
    val isUserLoggedIn by remember { mutableStateOf(false) }

    Column  {

        TopAppBar(
            title = {
                Text("Jeux", color = Color.White)
            },
            navigationIcon = {
                IconButton(onClick = {/*si on veut ajouter une navigation ici?*/ }) {
                    Icon(Icons.Filled.Casino, null,tint = Color.White)
                }

            },actions = {
                IconButton(onClick = {/* ajouter le fait de pouvoir partager le lien d'un jeu ?*/ }) {
                    Icon(Icons.Filled.Share, null,tint = Color.White)
                }
                IconButton(onClick = {/* Gérer la connexion/déconnexion ici */ }) {
                    Icon(
                        imageVector = if (isUserLoggedIn) Icons.Filled.Logout else Icons.Filled.Login,
                        contentDescription = null,tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.run { smallTopAppBarColors(containerColor = colorResource(id = R.color.monopoly_purple))}
        )


    }
}

// Liste des catégories
val categories = arrayListOf<Category>(
    Category(1, "Carte", R.drawable.carte_category_image),
    Category(2, "Enfant", R.drawable.enfant_category_image),
    Category(3, "Solo", R.drawable.solo_category_image),
    Category(4, "Extérieur", R.drawable.exterieur_category_image),
    // Ajoutez d'autres catégories avec leurs images
)
// Liste des nouveautés
val news = arrayListOf<NewsFeed>(
    NewsFeed(1, "Bang! Extension", R.drawable.bang_the_great_train_robbery_extension),
    NewsFeed(2, "Barcelona", R.drawable.barcelona),
    NewsFeed(3, "Café Del Gatto", R.drawable.cafe_del_gatto),
    NewsFeed(4, "Elios", R.drawable.elios),
    // Ajoutez d'autres catégories avec leurs images
)


@Composable
fun GamesScreen() {
    //J'avais trouvé ceci pour la naviguation au départ mais pas testé
    //val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 75.dp)

    ) {
        //Plateau() - si on veut ajouter le plateau en fond mais pas top je pense
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 42.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Première carte
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.monopoly_yellow),
                ),
                modifier = Modifier
                    .width(300.dp) // largeur de la card
                    .align(alignment = Alignment.CenterHorizontally)

            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp) //espace entre les élements
                ) {
                    Text("Catégories")
                    // Utilisation de LazyRow pour le défilement horizontal
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        //  accéder aux informations de chaque catégorie ici
                        items(categories) { Category ->

                            Column(
                                modifier = Modifier
                                    .size(width = 90.dp, height = 120.dp)
                                    .padding(top = 8.dp)
                                    .clickable {
                                        // Gérez la navigation vers la liste de jeux par catégorie
                                        //navController.navigate("listeJeux/${category.id}")
                                    },
                            ) {
                                Image(
                                    painter = painterResource(Category.imageResource),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .align(alignment = Alignment.CenterHorizontally)
                                        .padding(bottom = 8.dp)

                                )
                                Text(
                                    text = Category.name,
                                    modifier = Modifier
                                        .size(width = 90.dp, height = 50.dp)
                                        .wrapContentWidth(), //permet de prendre la place necessaire
                                    textAlign = TextAlign.Center // Centre le texte
                                )
                            }

                        }
                    }
                }
            }

            // Deuxième carte
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.monopoly_orange),
                ),
                modifier = Modifier
                    .width(300.dp) // largeur de la Card
                    .align(alignment = Alignment.CenterHorizontally)
                    .wrapContentHeight() // La hauteur s'ajustera en fonction du contenu

            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Nouveautés")
                    // Défilement avec lazyrow
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        //  accéder aux informations du fil d'actu
                        items(news) { NewsFeed ->

                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(top = 8.dp)
                                    .clickable {
                                        // Gérez la navigation vers la liste de jeux par catégorie
                                        //navController.navigate("Nouveautés/${NewsFeed.id}"?)
                                    },
                                //contentAlignment = Alignment.BottomCenter
                            ) {

                                Image(
                                    painter = painterResource(NewsFeed.imageResource),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .align(alignment = Alignment.CenterHorizontally)
                                        .padding(bottom = 8.dp)
                                )
                                Text(
                                    text = NewsFeed.name,
                                    modifier = Modifier
                                        .size(width = 90.dp, height = 50.dp)
                                        .wrapContentWidth(),
                                    textAlign = TextAlign.Center // Centre le texte

                                )
                            }

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun NavigationBarSample() {
    //gestion d'état /propriété d'état mutable qui stocke une valeur entière
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationBottomItem("Accueil", R.drawable.home),
        NavigationBottomItem("Jeux", R.drawable.dice),
        NavigationBottomItem("Evènements", R.drawable.event),
        NavigationBottomItem("Profil", R.drawable.person)
    )

    NavigationBar (
        containerColor = colorResource(id = R.color.monopoly_purple),
        modifier = Modifier.fillMaxWidth(),
    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                },
                label = { Text(item.name,color = if (selectedItem == index) Color.Red else Color.White) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}



@Composable
fun GamesScreenPreview() {
    TestEcranJeuxTheme {
        GamesScreen()
    }
}


/**@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopAppBarSamplePreview() {
    TestEcranJeuxTheme {
        // Utilisez Scaffold pour créer la mise en page principale de votre application
        Scaffold(
            topBar = {
                // Ajoutez ici votre BottomBar ou NavigationBarSample
                TopAppBarSample()
            },
            content = {
                GamesScreen()
            },
            bottomBar = {
                // Ajoutez ici votre BottomBar ou NavigationBarSample
                NavigationBarSample()
            },
        )
    }
}**/
