package com.example.mptcool_33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                buttonPrichinajalobu()
                buttonExitFromAcc()
                buttonDeleteAcc()
                menu1()
            }
        }
    }
}

//для шрифта
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Montserrat")
val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)


//метод чтобы загорался только один radiobutton
fun setRadioButtonState(selectedState: MutableState<Boolean>, otherStates: List<MutableState<Boolean>>) {
    selectedState.value = true
    otherStates.forEach { it.value = false }
}

//pop up причина жалобы внутри кнопки
@Composable
private fun buttonPrichinajalobu() {

    var isDialogVisible by remember { mutableStateOf(false) }


    Button(
        onClick = {
            isDialogVisible = true
        }
    ) {
        Text(text = "Жалоба")
    }

//open pup up
    if (isDialogVisible) {
        Dialog(

            onDismissRequest = {
                isDialogVisible = false
            },

            )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)

                    //.width(320.dp)
                    //.height(296.dp)
                    .background(
                        color = Color(0xff333333),
                        shape = RoundedCornerShape(size = 12.dp),
                    )
            )
            {

//заголовок и полоска
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        /*.fillMaxWidth(0.8f)
                            .fillMaxHeight(0.65f)*/
                       // .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(paddingValues = PaddingValues(all = 12.dp)),
                        text = "Причина жалобы",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.8.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFE6E6E6)
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999))
                    )
//список элементов radiobutton | LazyColumn - прокрутка элементов
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f)
                            .padding(10.dp),
                        state = rememberLazyListState()
                    ) {
                        items(1) { index ->
                            Column(

                                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                                horizontalAlignment = Alignment.Start,
                            ) {

                                val spamState = remember { mutableStateOf(false) }
                                val offensiveState = remember { mutableStateOf(false) }
                                val suicideState = remember { mutableStateOf(false) }
                                val adultContentState = remember { mutableStateOf(false) }
                                val otherState = remember { mutableStateOf(false) }

                                val showTextFieldState = remember { mutableStateOf(false) }

                                Column(Modifier.selectableGroup()) {
                                    Row(
                                        modifier = Modifier
                                    ) {
                                        RadioButton(
                                            selected = spamState.value,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color.White,
                                                unselectedColor = Color.White
                                            ),
                                            onClick = {
                                                setRadioButtonState(
                                                    spamState,
                                                    listOf(
                                                        offensiveState,
                                                        suicideState,
                                                        adultContentState,
                                                        otherState
                                                    )
                                                )
                                                showTextFieldState.value = false
                                            },
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = "Спам",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            )
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                    ) {
                                        RadioButton(
                                            selected = offensiveState.value,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color.White,
                                                unselectedColor = Color.White
                                            ),
                                            onClick = {
                                                setRadioButtonState(
                                                    offensiveState,
                                                    listOf(
                                                        spamState,
                                                        suicideState,
                                                        adultContentState,
                                                        otherState
                                                    )
                                                )
                                                showTextFieldState.value = false
                                            },
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = "Оскорбительный характер",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            )
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                    ) {
                                        RadioButton(
                                            selected = suicideState.value,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color.White,
                                                unselectedColor = Color.White
                                            ),
                                            onClick = {
                                                setRadioButtonState(
                                                    suicideState,
                                                    listOf(
                                                        spamState,
                                                        offensiveState,
                                                        adultContentState,
                                                        otherState
                                                    )
                                                )
                                                showTextFieldState.value = false
                                            },
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = "Призыв к суициду",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            )
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                    ) {
                                        RadioButton(
                                            selected = adultContentState.value,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color.White,
                                                unselectedColor = Color.White
                                            ),
                                            onClick = {
                                                setRadioButtonState(
                                                    adultContentState,
                                                    listOf(
                                                        spamState,
                                                        suicideState,
                                                        offensiveState,
                                                        otherState
                                                    )
                                                )
                                                showTextFieldState.value = false
                                            },
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = "Материалы 18+",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            )
                                        )
                                    }



                                    Row(
                                        modifier = Modifier
                                    ) {

                                        RadioButton(
                                            selected = otherState.value,
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color.White,
                                                unselectedColor = Color.White
                                            ),
                                            onClick = {
                                                setRadioButtonState(
                                                    otherState,
                                                    listOf(
                                                        spamState,
                                                        suicideState,
                                                        adultContentState,
                                                        offensiveState
                                                    )
                                                )
                                                showTextFieldState.value = otherState.value
                                            },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)


                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = "Другое",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = fontFamily,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            )
                                        )
                                    }
//открытие текстового поля при выборе другое
                                    var isError = false;
                                    var otherText by remember { mutableStateOf(TextFieldValue("")) }
                                    var text = otherText.text

                                        //проверка на длину текста в поле
                                    if (showTextFieldState.value) {
                                        if(text.length>10){
                                            isError = true
                                        }
                                        //val length = otherText.length
                                            BasicTextField(
                                            value = otherText,
                                            onValueChange = {
                                                                otherText = it


                                                            },
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .fillMaxWidth()
                                                .height(100.dp)
                                                .background(
                                                    color = colorResource(id = R.color.SvetloBlack),
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .border(
                                                    1.dp,
                                                    color = if (isError) Color(0xFFD20C34) else colorResource(
                                                        id = R.color.BorderBlack
                                                    ),
                                                    shape = RoundedCornerShape(12.dp)
                                                ),
                                            textStyle = TextStyle(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                                color = Color(0xFFE6E6E6),
                                            ),
                                            decorationBox = { innerTextField ->
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(8.dp)
                                                ) {
                                                    innerTextField()
                                                }
                                            }
                                        )

//показ текста о ошибке ввода
                                        if(isError)
                                        {

                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                                                verticalAlignment = Alignment.Top,
                                            ) {
                                                Box(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp)
                                                    .background(
                                                        color = Color(0xFF999999),
                                                        shape = RoundedCornerShape(size = 8.dp)
                                                    )
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 8.dp,
                                                        end = 8.dp,
                                                        bottom = 8.dp
                                                    )
                                                )
                                                {
                                                    Text(
                                                        text = "Превышено максимально допустимое число символов ",
                                                        style = TextStyle(
                                                            fontSize = 14.sp,
                                                            fontFamily = fontFamily,
                                                            fontWeight = FontWeight(400),
                                                            color = Color(0xFFE6E6E6),
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }


                                }


                            }

                        }
                    }
//кнопки снизу
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        TextButton(
                            modifier = Modifier
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .width(127.dp)
                                .height(36.dp),
                            onClick = {
                                // Закрываем модальное окно

                            }
                        ) {
                            Text(color = colorResource(id = R.color.white),
                                text = "Закрыть",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD20C34)),
                            modifier = Modifier
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .width(145.dp)
                                .height(36.dp),
                            onClick = {
                                // отправка жалобы

                            }

                        ) {
                            Text(color = colorResource(id = R.color.white),
                                text = "Пожаловаться",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

//pop up выйти из аккаунта
@Composable
private fun buttonExitFromAcc() {

    var isDialogVisible by remember { mutableStateOf(false) }


    Button(
        onClick = {
            isDialogVisible = true
        }
    ) {
        Text(text = "Выход")
    }

//pop up
    if (isDialogVisible) {
        Dialog(

            onDismissRequest = {
                isDialogVisible = false
            },

            )
        {
            Box(
                modifier = Modifier
                    //.fillMaxWidth(0.85f)
                    //.fillMaxHeight(0.2f)

                    .width(232.dp)
                    .height(126.dp)
                    .background(
                        color = Color(0xff333333),
                        shape = RoundedCornerShape(size = 12.dp),
                    )
            )

            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    /*.fillMaxWidth(0.8f)
                        .fillMaxHeight(0.65f)*/
                    // .padding(16.dp)
                )
                {
//заголовок
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues = PaddingValues(all = 8.dp))
                            .wrapContentWidth(Alignment.CenterHorizontally),

                        text = "Вы точно хотите \nвыйти из аккаута?",
                        style = TextStyle(
                            fontSize = 16.sp,
                            //lineHeight = 9.24.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFE6E6E6),
                            textAlign = TextAlign.Center
                        ),
                        textAlign = TextAlign.Center
                    )
                        //полоска
                    Spacer(
                        modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(PaddingValues(top = 5.dp)),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        //кнопка нет
                        TextButton(
                            modifier = Modifier
                                .size(78.dp, 36.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically),
                            onClick = {

                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 8.dp),
                                color = colorResource(id = R.color.white),
                                text = "Нет",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                ),
                            )
                        }
                            //кнопка выйти из аккаунта
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD20C34)),
                            modifier = Modifier
                                .size(98.dp, 36.dp)
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically),
                            onClick = {

                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 8.dp),
                                color = colorResource(id = R.color.white),
                                text = "Выйти",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                ),
                            )
                        }
                    }

                }
            }
        }
    }
}


//pop up удалить аккаунт
@Composable
private fun buttonDeleteAcc()
{
    var isDialogVisible by remember { mutableStateOf(false) }


    Button(
        onClick = {
            isDialogVisible = true
        }
    ) {
        Text(text = "удалить")
    }

//pop up
    if (isDialogVisible) {
        Dialog(

            onDismissRequest = {
                isDialogVisible = false
            },

            )
        {
            Box(
                modifier = Modifier
                    //.fillMaxWidth()
                    //.fillMaxHeight(0.2f)

                    .width(304.dp)
                    .height(156.dp)
                    .background(
                        color = Color(0xff333333),
                        shape = RoundedCornerShape(size = 12.dp),
                    )
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    /*.fillMaxWidth(0.8f)
                        .fillMaxHeight(0.65f)*/
                    // .padding(16.dp)
                )
                {
 //заголовок
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues = PaddingValues( top = 12.dp, start = 13.dp,bottom = 12.dp, end = 39.dp))
                            .wrapContentWidth(Alignment.CenterHorizontally),

                        text = "Вы точно хотите удалить аккаунт?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.6.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFE6E6E6),
                            textAlign = TextAlign.Center
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999))
                    )
//описание
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues = PaddingValues(top = 12.dp))
                            .wrapContentWidth(Alignment.CenterHorizontally),

                        text = "Аккаунт невозможно будет\nвосстановить и вернуть",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 17.4.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFE6E6E6),
                        ),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                            //.padding(PaddingValues(top = 16.dp)),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
//кнопка отменить
                        TextButton(
                            modifier = Modifier
                                .size(120.dp, 34.dp)
                                .fillMaxHeight()
                                //.fillMaxWidth()
                                //.padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically),
                            onClick = {

                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    //.padding(horizontal = 8.dp)
                                ,
                                color = colorResource(id = R.color.white),
                                text = "Отменить",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
//кнопка удалить
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD20C34)),
                            modifier = Modifier
                                .size(120.dp, 34.dp)
                                .fillMaxHeight()
                                //.fillMaxWidth()
                                //.padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically),
                            onClick = {

                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    //.padding(horizontal = 8.dp)
                                ,
                                color = colorResource(id = R.color.white),
                                text = "Удалить",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFE6E6E6),
                                    textAlign = TextAlign.Center
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
        }
    }
}

//pop up меню 2 строчка 4 модель
@Composable
private fun menu1() {
    var isDialogVisible by remember { mutableStateOf(false) }

    Button(onClick = { isDialogVisible = true }) {
        Text(text = "меню 1")
    }

    //pop up
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { isDialogVisible = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.25f)
                    .background(
                        color = Color(0xff333333),
                        shape = RoundedCornerShape(size = 12.dp),
                    )
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp),
                    state = rememberLazyListState()
                ) {
                    var textext = "Отключить уведомления"
                    items(1) { index ->

                        RowItem(textext, R.drawable.notifications_off_foreground,
                            onClick = {
                            // Обработка клика
                                textext = "asdasd"
                        })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Удалить группу", R.drawable.delete_icon_foreground,
                            onClick = {
                            // Обработка клика
                                //isDialogVisible = false
                        })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Поделиться", R.drawable.send_icon_foreground,
                            onClick = {
                            // Обработка клика
                        })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Настройки", R.drawable.settings_icon_foreground,
                            onClick = {
                                // Обработка клика
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun RowItem(text: String, imageResId: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            colorFilter = ColorFilter.tint(Color.White),
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight(500),
                color = Color(0xFFE6E6E6),
            ),
            modifier = Modifier
                .weight(1f) // Занимает оставшееся пространство
        )
    }
}


