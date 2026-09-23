package kr.ac.kumoh.ce.s20220437.s26w04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20220437.s26w04.ui.theme.S26W04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W04Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var count1 by remember { mutableIntStateOf(0) }
    var count2 by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Counter(
                modifier = Modifier.background(Color(0xFFE8DEF8)),
                count = count1
            ) {
                count1 = it
            }

            Counter(
                modifier = Modifier.background(Color(0XFFE9F680)),
                count = count2
            ) {
                count2 = it
            }
        }
    }
}

@Composable
fun ColumnScope.Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onChangeCount: (Int) -> Unit,
) {
//    var count by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .weight(1F)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0xFFFE7A36)),
            color = Color.White,
            fontSize = 100.sp,
            textAlign = TextAlign.Center,
        )

        Row {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onClick = {
                    onChangeCount(count + 1)
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_add),
                    contentDescription = "증가 버튼"
                )
            }
            Button(
                modifier = Modifier
                    .padding(8.dp),
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_more),
                    contentDescription = "다른 버튼들"
                )
            }
        }

        AnimatedVisibility(expanded) {
            Row {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        onChangeCount(count - 1)
                        expanded = false
                    }
                ) {
                    Text("감소")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        onChangeCount(0)
                        expanded = false
                    }
                ) {
                    Text("초기화")
                }
            }
        }
    }
}