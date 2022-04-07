package com.mmisoft.features.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import coil.compose.SubcomposeAsyncImage
import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.ui.theme.TeamFragmentSurfaceBackground
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballTeamFragment : Fragment() {

    private var footballTeam: FootballTeam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<FootballTeam>("SelectedTeam")?.let { footballTeam ->
            this.footballTeam = footballTeam
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent { 
                if(footballTeam == null){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Error Loading The Football Team",
                            style = MaterialTheme.typography.h2
                        )
                    }
                }else {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "${footballTeam?.name}", color = Color.White )
                                },
                                backgroundColor = TeamFragmentSurfaceBackground,
                                navigationIcon = {
                                        IconButton(onClick = { this.findNavController().popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowBack,
                                                contentDescription = "Back",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                )
                            },

                        content = {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xff333333)),
                                ) {

                                    Spacer(modifier = Modifier.height(60.dp))
                                    
                                    footballTeam?.image?.let { url ->
                                        SubcomposeAsyncImage(
                                            model = url,
                                            loading = {
                                                CircularProgressIndicator(modifier = Modifier.height(110.dp))
                                            },
                                            contentDescription = "Football Team Logo",
                                            modifier = Modifier
                                                .height(150.dp)
                                                .align(Alignment.CenterHorizontally),
                                            contentScale = ContentScale.Fit
                                        )

                                        footballTeam?.country?.let { it -> Text(
                                            text = it,
                                            modifier = Modifier
                                                .align(Alignment.Start)
                                                .padding(bottom = 8.dp, start = 5.dp),
                                            style = MaterialTheme.typography.h5,
                                            color = Color.White,
                                        ) }
                                }
                            }
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 3.dp, vertical = 5.dp))
                                {
                                    Text(style = MaterialTheme.typography.subtitle1,
                                        text = buildAnnotatedString {
                                            append("Der Club ")
                                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                                append("${footballTeam?.name}")
                                            }
                                            append(" aus ${footballTeam?.country} hat einen Wert von ${footballTeam?.value} Millionen Euro")
                                            }
                                    )
                                    Text(
                                        style = MaterialTheme.typography.body1,
                                        text = "${footballTeam?.name} konnte bislang ${footballTeam?.european_titles} Siege auf europäischer Ebene erreichen"
                                    )
                                }

                                Column (
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally

                                ){
                                    Text(text = "Stadium: ${footballTeam?.stadium?.name}")
                                    Text(text = "Mit der Größe: ${footballTeam?.stadium?.size}")
                                    Button(
                                        onClick = {
                                            val gmmIntentUri = Uri.parse("geo:0,0?q=${footballTeam?.location?.lat},${footballTeam?.location?.lng}(${footballTeam?.stadium?.name})")
                                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                            mapIntent.setPackage("com.google.android.apps.maps")
                                            startActivity(mapIntent)
                                                  },
                                    ) {
                                        Text(text = "Siehe in Google Maps")
                                    }
                                }
                            }
                })
            }
        }
    }
}
}