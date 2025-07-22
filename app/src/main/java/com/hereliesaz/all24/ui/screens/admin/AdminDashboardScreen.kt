package com.hereliesaz.all24.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hereliesaz.all24.data.Submission

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    navController: NavController,
    viewModel: AdminDashboardViewModel = viewModel()
) {
    val submissions by viewModel.submissions.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pending Submissions") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        if (submissions.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No pending submissions.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(submissions) { submission ->
                    SubmissionCard(submission = submission, onClick = {
                        // Navigate to a detail screen for approval/denial
                    })
                }
            }
        }
    }
}

@Composable
private fun SubmissionCard(submission: Submission, onClick: () -> Unit) {
    Card(onClick = onClick) {
        ListItem(
            headlineContent = { Text(submission.name) },
            supportingContent = { Text(submission.address) },
            overlineContent = { Text(submission.category.uppercase()) }
        )
    }
}
