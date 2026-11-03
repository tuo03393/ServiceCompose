package edu.temple.servicecompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import edu.temple.servicecompose.ui.theme.ServiceComposeTheme

const val TIMER_KEY = "TIMER_KEY"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Timer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Timer(modifier: Modifier = Modifier) {

    Column(Modifier.fillMaxSize(), Arrangement.Top,
        Alignment.CenterHorizontally) {

        Text(
            text = "Enter Timer Starting Value!",
            modifier = modifier
        )

        var timerValue by rememberSaveable { mutableStateOf("") }

        val context = LocalContext.current

        TextField(
            value = timerValue,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { timerValue = it }
        )

        Button(
            onClick = {
                context.startService(
                    Intent(context, MyTimerService::class.java).apply {
                        putExtra(TIMER_KEY, timerValue.toInt())
                        //In a more serious program, we would want to implement a check to ensure
                        //We are getting passed an Int.
                    }
                )
            }
        ) { Text("START") }

    }

    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServiceComposeTheme {
        Timer()
    }
}