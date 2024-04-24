import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Color

@Composable
fun Usuario(
    usuario: String,
    onUsuarioChange: (String) -> Unit
) {
    OutlinedTextField(
        value = usuario,
        onValueChange = onUsuarioChange,
        label = { Text("Usuario") },
        placeholder = { Text("Introduce un usuario: ") }
    )
}

@Composable
fun Password(
    passw: String,
    onPasswordChange: (String) -> Unit,
    contraseniaVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {


    OutlinedTextField(
        value = passw,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        visualTransformation = if (contraseniaVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = contraseniaVisible,
                onCheckedChange = onVisibilityChange
            ) {
                Icon(
                    imageVector = if (contraseniaVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Visibilidad"
                )
            }
        }
    )
}

@Composable
fun Boton(
    botonEncendido: Boolean,
    onBotonClick: () -> Unit
) {
    Button(
        onClick = onBotonClick,
        enabled = botonEncendido
    ) {
        Text(text = "Login")
    }
}

@Composable
@Preview
fun LoginScreen() {
    var usuario by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }
    val botonEncendido = usuario.isNotEmpty() && contrasenia.isNotEmpty()
    var contraseniaVisible by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            Usuario(usuario) {
                usuario = it
            }
            Password(contrasenia,{contrasenia = it},contraseniaVisible) {
                contraseniaVisible = it
            }
            Boton(botonEncendido) {
                usuario = ""
                contrasenia = ""
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Mi Login") {
        LoginScreen()
    }
}
