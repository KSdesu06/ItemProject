import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screen.ItemsScreen
import viewmodel.ItemViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(appModule: AppModule) {
    MaterialTheme {
        val viewModel = getViewModel(key = "items-screen", factory = viewModelFactory { ItemViewModel(appModule.repository) })
        ItemsScreen(viewModel)
    }
}
