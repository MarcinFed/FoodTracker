package com.example.foodtracking

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodtracking.Databases.ShoppingList.ListDB
import com.example.foodtracking.Databases.ShoppingList.ListItem
import com.example.foodtracking.Databases.ShoppingList.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ListViewModel
    private lateinit var database: ListDB
    private lateinit var application: Application
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        application = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(application, ListDB::class.java)
            .allowMainThreadQueries()
            .build()
        viewModel = ListViewModel(application, database)
    }

    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun insertItemAndRetrieve() = testDispatcher.runBlockingTest {
        val item = ListItem("Apples", 2f, "Kg", false)
        viewModel.insertItem(item)
        val items = viewModel.allItems.first()
        assertTrue("Item should be in the list", items.contains(item))
    }

    @Test
    fun deleteItem() = testDispatcher.runBlockingTest {
        val item = ListItem("Oranges", 1f, "Kg", true)
        viewModel.insertItem(item)
        viewModel.deleteItem(item)
        val items = viewModel.allItems.first()
        assertFalse("Item should not be in the list", items.contains(item))
    }

    @Test
    fun checkAndUncheckItem() = testDispatcher.runBlockingTest {
        val item = ListItem("Bananas", 1.5f, "Kg", false)
        viewModel.insertItem(item)
        viewModel.checkItem("Bananas", true)
        val items = viewModel.allItems.first()
        val updatedItem = items.find { it.Product == "Bananas" }
        assertNotNull(updatedItem)
        assertTrue("Item should be marked as bought", updatedItem?.Bought ?: false)
    }

    @Test
    fun checkAllItems() = testDispatcher.runBlockingTest {
        viewModel.insertItem(ListItem("Milk", 1f, "Liters", false))
        viewModel.insertItem(ListItem("Eggs", 12f, "Units", false))
        viewModel.checkAllItems(true)
        val items = viewModel.allItems.first()
        assertTrue("All items should be marked as bought", items.all { it.Bought })
    }

    @Test
    fun deleteCheckedItems() = testDispatcher.runBlockingTest {
        viewModel.insertItem(ListItem("Bread", 2f, "Loaves", true))
        viewModel.insertItem(ListItem("Butter", 1f, "Kg", true))
        viewModel.deleteCheckedItems()
        val items = viewModel.allItems.first()
        assertTrue("List should be empty after deleting checked items", items.isEmpty())
    }
}
