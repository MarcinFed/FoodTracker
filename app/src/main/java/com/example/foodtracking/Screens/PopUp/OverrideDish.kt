import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.foodtracking.R

@Composable
fun ConfirmDishChangeDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    date: String,
    oldDishName: String,
    newDishName: String,
) {
    val context = LocalContext.current
    val (showDialog, setShowDialog) = remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                setShowDialog(false)
                onDismiss()
            },
            title = {
                Text(text = "Confirm Change")
            },
            text = {
                Text("Are you sure you want to change dish from $oldDishName to $newDishName on $date?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text("Confirm", color = colorResource(id = R.color.blue))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                        onDismiss()
                    }
                ) {
                    Text("Cancel", color = Color.Black)
                }
            }
        )
    }
}