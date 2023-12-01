import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import dev.icerock.moko.mvvm.compose.ViewModelFactory
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.AppModule
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import repository.ItemRepository
import viewmodel.ItemViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(appModule: AppModule) {
    MaterialTheme {
        val viewModel = getViewModel(key = "items-screen", factory = viewModelFactory { ItemViewModel(appModule.repository) })
        ItemsScreen(viewModel)
    }
}
