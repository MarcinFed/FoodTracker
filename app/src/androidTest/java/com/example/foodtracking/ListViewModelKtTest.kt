import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.Databases.ShoppingList.ListDB
import com.example.foodtracking.Databases.ShoppingList.ListDao
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListRepository
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ListViewModel
    private lateinit var listRepository: ListRepository
    private val application: Application = mockk(relaxed = true)
    private val listDB: ListDB = mockk(relaxed = true)
    private val listDao: ListDao = mockk(relaxed = true)
    @Before
    fun setup() {
        // Mock the DAO and repository
        coEvery { listDB.listDao() } returns listDao
        listRepository = ListRepository(listDao)

        // Initialize ViewModel
        viewModel = ListViewModel(application, listDB)
    }

    @Test
    fun insert_item_adds_to_repository() = runBlockingTest {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)

        // Verify that the item is added to the repository
        coEvery { listRepository.addItem(listItem) }
        viewModel.allItems.value.contains(listItem).let { assertTrue(it) }
    }

    @Test
    fun delete_item_removes_from_repository() = runBlockingTest {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem) // Add first to delete later
        viewModel.deleteItem(listItem)

        // Verify that the item is removed from the repository
        coEvery { listRepository.deleteItem(listItem) }
        viewModel.allItems.value.contains(listItem).let { assertFalse(it) }
    }

    @Test
    fun modify_item_updates_item_details() = runBlockingTest {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)
        viewModel.modifyItem("Tomato", 2f, "kg", true)

        // Check that the item has been updated
        assertEquals(viewModel.allItems.value.first { it.Product == "Tomato" }.Amount, 2f)
        assertTrue(viewModel.allItems.value.first { it.Product == "Tomato" }.Bought)
    }
}
