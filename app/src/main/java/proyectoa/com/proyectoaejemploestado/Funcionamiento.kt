package proyectoa.com.proyectoaejemploestado

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun pantallaFuncionamiento(navController: NavController){
    var redondeoEntradaAlza by remember { mutableStateOf(false) }
    var redondeoEntradaNormal by remember { mutableStateOf(false) }
    var noRedondeoEntrada by remember { mutableStateOf(true)}
    var texto : String = ExplicacionSwitchs(redondeoEntradaAlza,redondeoEntradaNormal)

    Column(modifier = Modifier.statusBarsPadding()
        .padding(horizontal = 40.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = { navController.navigate("MainActivity") }, modifier = Modifier.padding(start = 50.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_arrow_forward_ios_24),
                        contentDescription = "flecha hacia delante"
                    )
                }
            }


        Text("Funcionamiento de la app", fontSize = 25.sp, modifier = Modifier,)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Aqui pondremos el importe total",fontSize = 15.sp)
        CampoNumero( label = R.string.importe_base,
            leadingIcon = R.drawable.moneda,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth())



        Spacer(modifier = Modifier.height(5.dp))
        Text("Aqui pondremos el porcentaje de beneficio que vamos a obtener", modifier = Modifier,fontSize = 15.sp)
        CampoNumero( label = R.string.importe_beneficio,
            leadingIcon = R.drawable.porcentaje,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth())


        Spacer(modifier = Modifier.height(25.dp))
        CampoNoRedondeoEx(
            roundUp = noRedondeoEntrada,
            onRoundUpChanged = {

                noRedondeoEntrada = it
                if (noRedondeoEntrada) {
                    redondeoEntradaAlza = false
                    redondeoEntradaNormal = false
                }

                if (!noRedondeoEntrada && !redondeoEntradaAlza && !redondeoEntradaNormal)
                    redondeoEntradaAlza = true
            },

        )

        CampoRedondeoAlzaEx(
            roundUp = redondeoEntradaAlza,
            onRoundUpChanged = {
                redondeoEntradaAlza = it

                if (redondeoEntradaAlza) {
                    redondeoEntradaNormal = false
                    noRedondeoEntrada = false
                }
            },
            modifier = Modifier.padding(bottom = 10.dp)
        )

        CampoRedondeoNormalEx(
            roundUp = redondeoEntradaNormal,
            onRoundUpChanged = {
                redondeoEntradaNormal = it

                if (redondeoEntradaNormal) {
                    redondeoEntradaAlza = false
                    noRedondeoEntrada = false
                }
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(texto, modifier = Modifier,fontSize = 15.sp)
        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = {}) {
           Text("Copiar")
       }
        Spacer(modifier = Modifier.height(5.dp))
      Text("Al darle a este boton se copiara el importe en nuestro portapapeles", modifier = Modifier, fontSize = 15.sp)






    }

}
@Composable
fun CampoNumero(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    modifier: Modifier = Modifier
) {
    var texto by remember { mutableStateOf("") } // estado para que el TextField funcione

    TextField(
        value = texto,
        onValueChange = { texto = it },
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
        label = { Text(stringResource(label)) },
        modifier = modifier
    )
}
@Composable
fun CampoNoRedondeoEx(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.sin_redondeo))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}
@Composable
fun CampoRedondeoAlzaEx(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.redondear_beneficio_alza))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}
@Composable
fun CampoRedondeoNormalEx(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.redondear_beneficio))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}

fun ExplicacionSwitchs( redondearAlza: Boolean,
                        redondearNormal: Boolean): String{
    var texto: String
    if (redondearAlza){
        texto= "con este Switch activo siempre redondeas el calculo hacia arriba"
    }else if (redondearNormal){
        texto="con este Switch activo redondeas normal mas de 50 hacia arriba si no se queda igual "
    }else{
        texto="con este Switch activo no se redondea y se queda el calculo con decimales"
    }
    return texto
}
