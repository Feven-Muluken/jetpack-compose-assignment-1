package com.example.coursecatalog


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.coursecatalog.ui.theme.CourseCatalogTheme
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.text.font.FontFamily

data class Course(
    val title: String,
    val code: String,
    val credits: Int,
    val description: String,
    val prerequisites: String,
)
val catalogCourse = listOf(
    Course(
        "Introduction to Computer Science", "CS101", 3,
        "This course introduces the fundamentals of computing, problem-solving, and programming using Python. Students will learn basic algorithms, data types, control structures, and software development principles. Designed for students with no prior programming experience. Prepares students for further computer science study.",
        "None"
    ),
    Course(
        "Calculus I", "MATH101", 4,
        "Covers limits, derivatives, and integrals of single-variable functions. Emphasizes conceptual understanding and problem-solving. Applications in science and engineering are explored throughout the course. Requires strong algebra and trigonometry background.",
        "Pre-Calculus"
    ),
    Course(
        "General Chemistry", "CHEM101", 4,
        "Introduces atomic theory, chemical bonding, stoichiometry, and chemical reactions. Includes lab work to reinforce lecture content. Students learn to apply chemical principles in practical situations. Designed for science and engineering majors.",
        "High School Chemistry"
    ),
    Course(
        "English Composition", "ENG101", 3,
        "Focuses on academic writing, critical thinking, and clear communication. Students write essays, analyze texts, and learn citation styles. Emphasizes grammar, structure, and editing. Builds a foundation for writing in all disciplines.",
        "None"
    ),
    Course(
        "Introduction to Psychology", "PSY101", 3,
        "Explores the scientific study of behavior and mental processes. Topics include learning, memory, emotion, and personality. Covers major psychological theories and research methods. Encourages application to everyday life.",
        "None"
    ),
    Course(
        "Principles of Microeconomics", "ECON101", 3,
        "Examines individual and firm decision-making in markets. Topics include supply and demand, elasticity, and market efficiency. Analyzes real-world economic issues and consumer behavior. No prior economics knowledge required.",
        "None"
    ),
    Course(
        "Introduction to Sociology", "SOC101", 3,
        "Investigates the structure and dynamics of societies and social groups. Topics include culture, inequality, and institutions. Introduces sociological theories and methods of research. Encourages critical thinking about social issues.",
        "None"
    ),
    Course(
        "Physics I: Mechanics", "PHYS101", 4,
        "Covers motion, force, energy, momentum, and circular motion. Uses calculus in problem-solving and lab experiments. Builds a strong foundation for engineering and physics majors. Includes hands-on lab experiments to reinforce theory.",
        "Calculus I (Co-requisite)"
    ),
    Course(
        "Introduction to Philosophy", "PHIL101", 3,
        "Surveys major philosophical questions and thinkers from ancient to modern times. Topics include ethics, metaphysics, and knowledge. Encourages analytical thinking and reasoned debate. No prior philosophy background needed.",
        "None"
    ),
    Course(
        "World History", "HIST101", 3,
        "Explores the major civilizations and events from ancient to modern times. Emphasizes global interactions, conflicts, and cultural developments. Develops skills in historical analysis and interpretation. Designed for students of all majors.",
        "None"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseCatalogTheme {
                var started by rememberSaveable { mutableStateOf(false) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    if (started) {
                        CourseListScreen(courses = catalogCourse)
                    } else {
                        WelcomeScreen(onStart = { started = true })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListScreen(courses: List<Course>) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Course Catalogue")
                    }
                )
            }
        }
    ) { innerPadding ->
        CourseListContent(
            courses = courses,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun CourseListContent(
    courses: List<Course>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer // Light background
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = course.code,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${course.credits} Credits",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description: ${course.description}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.SansSerif)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Prerequisites: ${course.prerequisites}",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to CourseCatalog",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onStart) {
            Text("Get Started")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseListScreen() {
    CourseCatalogTheme {
        CourseListScreen(courses = catalogCourse)
    }
}