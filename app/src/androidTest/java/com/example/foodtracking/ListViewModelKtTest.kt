import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.Databases.ShoppingList.ListDB
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
    private lateinit var database: ListDB
    private lateinit var application: Application

    private fun createNewDatabaseInstance(): ListDB {
        return inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ListDB::class.java
        ).allowMainThreadQueries().build()
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        application = ApplicationProvider.getApplicationContext()
        database = createNewDatabaseInstance()
        viewModel = ListViewModel(application, database)
    }

    @After
    fun tearDown() = runTest {
        database.clearAllTables()
        database.close()
        database = createNewDatabaseInstance()
    }

    @Test
    fun testSaveAndRetrieveItem() = runBlocking {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)

        val retrievedItem = viewModel.getItem("Tomato")
        assertEquals(listItem, retrievedItem)
    }

    @Test
    fun testModifyItem() = runBlocking {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)

        viewModel.modifyItem("Tomato", 2f, "kg", true)
        val modifiedItem = viewModel.getItem("Tomato")
        assertEquals(2f, modifiedItem.Amount)
        assertEquals("kg", modifiedItem.Unit)
        assertTrue(modifiedItem.Bought)
    }

    @Test
    fun testDeleteItem() = runBlocking {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)

        viewModel.deleteItem(listItem)
        val deletedItem = viewModel.getItem("Tomato")
        assertNull(deletedItem)
    }

    @Test
    fun testSubstractItem() = runBlocking {
        val listItem = ListItem("Tomato", 2f, "kg", false)
        viewModel.insertItem(listItem)

        viewModel.substractItem("Tomato", 1f, "kg", false)
        val substractedItem = viewModel.getItem("Tomato")
        assertEquals(1f, substractedItem.Amount)
    }

    @Test
    fun testCheckItem() = runBlocking {
        val listItem = ListItem("Tomato", 1f, "kg", false)
        viewModel.insertItem(listItem)

        viewModel.checkItem("Tomato", true)
        val checkedItem = viewModel.getItem("Tomato")
        assertTrue(checkedItem.Bought)
    }

    @Test
    fun testCheckAllItems() = runBlocking {
        val listItem1 = ListItem("Tomato", 1f, "kg", false)
        val listItem2 = ListItem("Apple", 1f, "kg", false)
        viewModel.insertItem(listItem1)
        viewModel.insertItem(listItem2)

        viewModel.checkAllItems(true)
        val checkedItem1 = viewModel.getItem("Tomato")
        val checkedItem2 = viewModel.getItem("Apple")
        assertTrue(checkedItem1.Bought)
        assertTrue(checkedItem2.Bought)
    }

    @Test
    fun testDeleteCheckedItems() = runBlocking {
        val listItem1 = ListItem("Tomato", 1f, "kg", true)
        val listItem2 = ListItem("Apple", 1f, "kg", false)
        viewModel.insertItem(listItem1)
        viewModel.insertItem(listItem2)

        viewModel.deleteCheckedItems()
        val deletedItem1 = viewModel.getItem("Tomato")
        val deletedItem2 = viewModel.getItem("Apple")
        assertNull(deletedItem1)
        assertNotNull(deletedItem2)
    }

    @Test
    fun testItemsChecked() = runBlocking {
        val listItem1 = ListItem("Tomato", 1f, "kg", true)
        val listItem2 = ListItem("Apple", 1f, "kg", false)
        viewModel.insertItem(listItem1)
        viewModel.insertItem(listItem2)

        assertTrue(viewModel.itemsChecked())
    }

}

