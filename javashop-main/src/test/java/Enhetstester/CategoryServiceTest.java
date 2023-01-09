package Enhetstester;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.CategoryRepository;
import se.systementor.supershoppen1.shop.services.CategoryService;

class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;


    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCategory() throws IOException {
        // Create a mock Category object
        Category mockCategory = mock(Category.class);
        mockCategory.setName("Mock Category");
        mockCategory.setDescription("This is a mock category");

        // Create a mock MultipartFile object
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "mock-category-image.jpg", "image/jpg", "image data".getBytes());

        // Set up mock repository behavior
        when(repository.save(mockCategory)).thenReturn(mockCategory);

        // Call the addCategory method
        Category addedCategory = categoryService.addCategory(mockCategory, mockMultipartFile);

        // Verify that the repository save method was called
        verify(repository, times(1)).save(mockCategory);

        // Assert that the returned value is not null
        assertNotNull(addedCategory);

        // Assert that the returned value is the same as the mock category
        assertEquals(mockCategory, addedCategory);
    }


    @Test
    void testEditCategory() throws IOException {
        // Create test category
        Category testCategory = new Category();
        testCategory.setId(1);
        testCategory.setName("Test Category");
        testCategory.setDescription("This is a test category.");

        // Create mock MultipartFile
        MockMultipartFile mockMultipartFile = new MockMultipartFile("testFile", "test.jpg", "image/jpg", "test data".getBytes());

        // Configure mock repository
        when(repository.findById(1)).thenReturn(Optional.of(testCategory));
        when(repository.save(any(Category.class))).thenReturn(testCategory);

        // Call editCategory method
        Category result = categoryService.editCategory(1, testCategory, mockMultipartFile);

        // Verify result
        assertEquals(testCategory, result);
    }
}
