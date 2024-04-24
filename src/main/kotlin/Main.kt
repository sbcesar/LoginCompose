import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {

    var usuario by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }
    val botonEncendido = usuario.isNotEmpty() && contrasenia.isNotEmpty()

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") }
            )
            var contraseniaVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it},
                label = { Text("Contraseña") },
                visualTransformation = if (contraseniaVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = contraseniaVisible, onCheckedChange = { contraseniaVisible = it }) {
                        Icon(
                            imageVector = if (contraseniaVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Visibilidad"
                        )
                    }
                }
            )
            Button(
                onClick = { usuario = ""; contrasenia = "" },
                enabled = botonEncendido
            ) {
                Text(text = "Login")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Mi Login") {
        App()
    }
}
