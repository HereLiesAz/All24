package com.hereliesaz.all24.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSubmissionDetailScreen(
    navController: NavController,
    viewModel: AdminSubmissionDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val submission = uiState.submission

    // Navigate back automatically after approval/denial
    LaunchedEffect(submission) {
        if (submission == null && !uiState.isLoading) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(submission?.name ?: "Loading...") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            submission != null -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    Text("Name: ${submission.name}", style = MaterialTheme.typography.titleMedium)
                    Text("Address: ${submission.address}")
                    Text("Description: ${submission.description}")
                    Spacer(Modifier.weight(1f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                viewModel.deny()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Deny")
                        }
                        Button(onClick = {
                            viewModel.approve()
                        }) {
                            Text("Approve")
                        }
                    }
                }
            }
            else -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(uiState.error ?: "Submission not found.")
                }
            }
        }
    }
}