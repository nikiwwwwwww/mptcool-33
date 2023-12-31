package com.example.mptcool_33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                //Spacer(modifier = Modifier.height(500.dp))
                buttonPrichinajalobu()
                buttonExitFromAcc()
                buttonDeleteAcc()
                menu1()
                menu2()

                BottomSheet1()
                BottomSheetWithList()
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
                            .padding(
                                paddingValues = PaddingValues(
                                    top = 12.dp,
                                    start = 13.dp,
                                    bottom = 12.dp,
                                    end = 39.dp
                                )
                            )
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

//иетод для меню
@Composable
fun RowItem(text: String, imageResId: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = imageResId),
            contentDescription = "image description",
            //contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .padding(end = 4.dp),
            colorResource(id = R.color.white)
            //colorFilter = ColorFilter.tint(Color.White),
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
                .weight(1f)
        )
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
                    items(1) { index ->
                        RowItem( "Отключить уведомления", R.drawable.notifications_off_foreground,
                            onClick = {
                            // Обработка клика
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
private fun menu2() {
    var isDialogVisible by remember { mutableStateOf(false) }

    Button(onClick = { isDialogVisible = true }) {
        Text(text = "меню 2")
    }

    //pop up
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = { isDialogVisible = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
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
                    items(1) { index ->
                        RowItem("Очистить историю", R.drawable.notifications_off_foreground,
                            onClick = {
                                // Обработка клика
                            })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Отключить уведомления", R.drawable.notifications_off_foreground,
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
                        RowItem("Разблокировать", R.drawable.send_icon_foreground,
                            onClick = {
                                // Обработка клика
                            })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Пожаловаться", R.drawable.complain_icon,
                            onClick = {
                                // Обработка клика
                            })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Изменить оформление чата", R.drawable.brush_icon,
                            onClick = {
                                // Обработка клика
                            })
                        Spacer(modifier = Modifier.height(4.dp))
                        Spacer(modifier = Modifier
                            .width(320.dp)
                            .height(1.dp)
                            .background(color = Color(0x26999999)))
                        Spacer(modifier = Modifier.height(4.dp))
                        RowItem("Поиск", R.drawable.search_icon,
                            onClick = {
                                // Обработка клика
                            })
                    }
                }
            }
        }
    }
}

//bottomsheet
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun BottomSheet1() {

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Box(

    ) {
        Button(
            onClick = {
                isSheetOpen = true
            }) {
            Text(text = "Bottom Sheet 1")
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            scrimColor = Color(0xFF1A1A1A),
            containerColor = Color(0xFF1A1A1A),
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
           )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A))
                    .height(230.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                    ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                    shape = RoundedCornerShape(size = 8.dp),
                    onClick = {
                        // Действия при нажатии на "Скопировать ссылку"
                    }
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.link_icon_foreground),
                        contentDescription = "image description",
                        //contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            //.padding(end = 2.dp)
                                ,
                        colorResource(id = R.color.white)
                        //colorFilter = ColorFilter.tint(Color.White),
                    )
                    Text(
                        text = "Скопировать ссылку",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.3.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFE6E6E6),

                            textAlign = TextAlign.Center,
                        )
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                    ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                    shape = RoundedCornerShape(size = 8.dp),
                    onClick = {
                        // Действия при нажатии на "Скопировать ссылку"
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.complain_icon),
                        contentDescription = "image description",
                        //contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                        //.padding(end = 2.dp)
                        ,
                        colorResource(id = R.color.white)
                        //colorFilter = ColorFilter.tint(Color.White),
                    )
                    Text("Пожаловаться",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.3.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFE6E6E6),

                            textAlign = TextAlign.Center,
                        ))
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                    ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                    shape = RoundedCornerShape(size = 8.dp),
                    onClick = {
                        // Действия при нажатии на "Скопировать ссылку"
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.visability_off_foreground),
                        contentDescription = "image description",
                        //contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                        //.padding(end = 2.dp)
                        ,
                        colorResource(id = R.color.white)
                        //colorFilter = ColorFilter.tint(Color.White),
                    )

                    Text("Скрыть",
                        style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.3.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFE6E6E6),

                        textAlign = TextAlign.Center,
                    ))
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithList() {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Box(

    ) {
        Button(
            onClick = {
                isSheetOpen = true
            }
        ) {
            Text(text = "Bottom Sheet 2")
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            scrimColor = Color(0xFF1A1A1A),
            containerColor = Color(0xFF1A1A1A),
            onDismissRequest = { isSheetOpen = false },
            modifier = Modifier.background(Color(0xFF1A1A1A))
        ) {
            Column(
                modifier = Modifier
                    //.fillMaxHeight()
                    .background(Color(0xFF1A1A1A))
                    .fillMaxWidth()
                    .height(370.dp)
                    .padding(16.dp)
            ) {
                LazyColumn(modifier = Modifier
                    .height(256.dp)) {
                    items (1){ item ->
                        ListItemWithButton(item = "элемент 1")
                        ListItemWithButton(item = "элемент 2")
                        ListItemWithButton(item = "элемент 3")
                        ListItemWithButton(item = "элемент 4")
                        ListItemWithButton(item = "элемент 5")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    //.padding(PaddingValues(top = 16.dp)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//кнопка отменить
                    TextButton(
                        modifier = Modifier
                            .size(157.dp, 48.dp)
                            //.fillMaxHeight()
                            //.fillMaxWidth()
                            //.padding(horizontal = 8.dp)
                            .align(Alignment.CenterVertically),
                        onClick = {

                        }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxHeight()
                            //.padding(horizontal = 8.dp)
                            ,
                            color = colorResource(id = R.color.white),
                            text = "Отменить",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFE6E6E6)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
//кнопка удалить
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD20C34)),
                        modifier = Modifier
                            .size(157.dp, 48.dp)
                            //.fillMaxHeight()
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

@Composable
fun ListItemWithButton(item: String) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(vertical = 8.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.3.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight(500),
                color = Color(0xFFE6E6E6),

                textAlign = TextAlign.Center,
            ))
        IconButton(
            onClick = {
                isChecked = !isChecked
            }
        ) {
            Icon(
                imageVector = if (isChecked) Icons.Default.Check else Icons.Default.Add,
                contentDescription = "Button icon",
                tint = Color.White
            )
        }
    }
}


