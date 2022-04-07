package com.mmisoft.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.ui.theme.*

@Composable
fun FootballTeamListItem(
    footballTeam: FootballTeam,
    onClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
        footballTeam.image?.let { url ->
            SubcomposeAsyncImage(
                model = url,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                    )
                },
                contentDescription = "Football Team Logo",
                modifier = Modifier
                    .height(110.dp),
                contentScale = ContentScale.Fit
            )
            /* Glide Implementation
            val image = loadPicture(url = url, defaultImage = com.allaboutapps.R.drawable.ic_launcher_background)
            image?.let { img ->
                img.value?.let {
                    Image(
                        it.asImageBitmap(),
                        contentDescription = "Empty plate",
                        modifier = Modifier
                            .height(110.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }*/
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(
                text = "${footballTeam.name}",
                style = MaterialTheme.typography.footballTeamListName,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "${footballTeam.country}",
                style = MaterialTheme.typography.footballTeamListCountry,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "${footballTeam.value} Millionen",
                style = MaterialTheme.typography.footballTeamListValue,
                modifier = Modifier.align(Alignment.End)
            )
        }

    }
    Divider(
        color = ListItemDivider,
        thickness = 1.dp
    )
}
