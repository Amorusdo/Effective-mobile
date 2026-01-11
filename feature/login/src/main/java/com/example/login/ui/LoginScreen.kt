package com.example.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core_ui.theme.Black
import com.example.core_ui.theme.Blue
import com.example.core_ui.theme.LoginTitle
import com.example.core_ui.theme.OrangeLight
import com.example.core_ui.theme.robotoFontFamily
import com.example.core_utils.constanse.Site.OK
import com.example.core_utils.constanse.Site.VK
import com.example.login.R
import com.example.login.widjet.RoundedTextField
import com.example.login.widjet.SocialButton


@Composable
fun LoginScreen(
    navController: NavController ,
    goToGeneralScreen: () -> Unit ,

    ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // твой контент


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) ,
            verticalArrangement = Arrangement.Center ,

            ) {

            Text(
                fontFamily = robotoFontFamily ,
                text = stringResource(R.string.enter) ,
                style = LoginTitle

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                fontFamily = robotoFontFamily ,
                text = stringResource(R.string.email) ,
                style = LoginTitle

            )

            Spacer(modifier = Modifier.height(6.dp))

            RoundedTextField(
                value = email ,
                onValueChange = { email = it } ,
                placeholder = "example@gmail.com"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                fontFamily = robotoFontFamily ,
                text = stringResource(R.string.password) ,
                style =  LoginTitle
            )

            Spacer(modifier = Modifier.height(6.dp))

            RoundedTextField(
                value = password ,
                onValueChange = { password = it } ,
                placeholder = "Введите пароль" ,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = goToGeneralScreen ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp) ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ) ,
                shape = RoundedCornerShape(26.dp)
            ) {
                Text(
                    text = "Вход" ,
                    color = Color.White ,
                    fontSize = 16.sp ,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Нет аккаунта? " ,
                    color = Color.LightGray ,
                    fontSize = 13.sp
                )
                Text(
                    text = "Регистрация" ,
                    color = Color(0xFF4CAF50) ,
                    fontSize = 13.sp ,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Забыл пароль" ,
                color = Color(0xFF4CAF50) ,
                fontSize = 13.sp ,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(28.dp))

            HorizontalDivider(thickness = 1.dp , color = Color.DarkGray)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SocialButton(
                    painter = painterResource(id = R.drawable.icon_vk) ,
                    color = Blue ,
                    url = VK ,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))

                SocialButton(
                    painter = painterResource(id = R.drawable.icon_ok) ,
                    color = OrangeLight ,
                    url = OK ,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}