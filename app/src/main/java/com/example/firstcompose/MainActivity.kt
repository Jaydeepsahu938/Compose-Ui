package com.example.firstcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcompose.ui.theme.AquaBlue
import com.example.firstcompose.ui.theme.Beige1
import com.example.firstcompose.ui.theme.Beige2
import com.example.firstcompose.ui.theme.Beige3
import com.example.firstcompose.ui.theme.BlueViolet1
import com.example.firstcompose.ui.theme.BlueViolet2
import com.example.firstcompose.ui.theme.BlueViolet3
import com.example.firstcompose.ui.theme.ButtonGreen
import com.example.firstcompose.ui.theme.DarkerButtonGreen
import com.example.firstcompose.ui.theme.DeepBlue
import com.example.firstcompose.ui.theme.LightGreen1
import com.example.firstcompose.ui.theme.LightGreen2
import com.example.firstcompose.ui.theme.LightGreen3
import com.example.firstcompose.ui.theme.LightRed
import com.example.firstcompose.ui.theme.OrangeYellow1
import com.example.firstcompose.ui.theme.OrangeYellow2
import com.example.firstcompose.ui.theme.OrangeYellow3
import com.example.firstcompose.ui.theme.TextWhite
import com.example.firstcompose.ui.theme.skyblue1
import com.example.firstcompose.ui.theme.skyblue2
import com.example.firstcompose.ui.theme.skyblue3


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // this is how we call
            // function adding whole UI
            Greetings()
            ChipSection(
                chips = listOf(
                    "Data structures",
                    "Algorithms",
                    "competitive programming",
                    "python"
                )
            )
            suggestionSection()
            CourseSection(
                courses = listOf(
                    Course(
                        title = "geek of the year",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Course(
                        title = "Advance python Course",
                        R.drawable.ic_play,
                        skyblue1,
                        skyblue2,
                        skyblue3
                    ),
                    Course(
                        title = "Advance Java Course",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    Course(
                        title = "prepare for aptitude test",
                        R.drawable.ic_play,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("explore", R.drawable.explore),
                BottomMenuContent("dark mode", R.drawable.outline_dark_mode_24),
                BottomMenuContent("videos", R.drawable.ic_videocam),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
            )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonGreen,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0

) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
   Row(
       horizontalArrangement = Arrangement.SpaceAround,
       verticalAlignment = Alignment.CenterVertically,
       modifier = modifier
           .fillMaxWidth()
           .background(DeepBlue)
           .padding(15.dp)
   ) {
       items.forEachIndexed { index, item ->
           BottomMenuItem(
               item = item,
               isSelected = index == selectedItemIndex,
               activeHighlightColor = activeHighlightColor,
               activeTextColor = activeTextColor,
               inactiveTextColor = inactiveTextColor
           ) {
               selectedItemIndex = index
           }
       }
   }

}


@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonGreen,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun Greetings() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = "Good Morning,Geeks",
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = "We wish you have a good day!",
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.serch_icon),
            contentDescription = "Search Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)

        )
    }
}


@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonGreen
                        else DarkerButtonGreen
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}


@Composable
fun suggestionSection(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightRed)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Coding",
                style = MaterialTheme.typography.titleLarge,
                color = TextWhite
            )
            Text(
                text = "do at least • 3-10 problems / day",
                style = MaterialTheme.typography.bodySmall,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonGreen)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun CourseSection(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Courses",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(15.dp),
            color = Color.White
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(courses.size) {
                CourseItem(course = courses[it])
            }
        }
    }
}

@Composable
fun CourseItem(
    course: Course
) {
    BoxWithConstraints(
        // Box with some attributes
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(course.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        // setting 5 points for medium
        // color or we can say for another
        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())
        // joining points to make curves with the help of path class
        // path file that we have created earlier
        // having function that just help to reduce our code
        // and the function is standardQuadFromTo(m1,m2) taking
        // two peramente and connect them
        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        // it's another part of that
        // texture with light color
        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        // canvas is used when we
        // want to draw something
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                // function for drawing paths
                // just pass the path
                path = mediumColoredPath,
                color = course.mediumColor
            )
            drawPath( // it's for the lighter path
                path = lightColoredPath,
                color = course.lightColor
            )
        }
        // so , we have done with texture and
        // now just creating box and other things
        // box containing course elements
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = course.iconId),
                contentDescription = course.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the clicks
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonGreen)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

