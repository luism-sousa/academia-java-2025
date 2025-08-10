import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.repositories.ShelfRepository;
import pt.upacademy.jseproject.maven.services.ShelfService;

public class ShelfTest {
    private ShelfService SS;

    @BeforeEach
    void constructorSetup() {
        SS = new ShelfService();
        ShelfRepository.getInstance().clear();
    }

    @Test
    void testCreateShelf() {
        Shelf newShelf = new Shelf(100, 50.0);
        SS.create(newShelf);

        assertNotNull(newShelf.getId());
        assertEquals(100, newShelf.getCapacity());
    }

    @Test
    void testReadShelfById() {
        Shelf newShelf = SS.create(new Shelf(100, 50.0));
        Shelf foundShelf = SS.findById(newShelf.getId());

        assertNotNull(foundShelf); 
        assertEquals(newShelf.getCapacity(), foundShelf.getCapacity());
    }

    @Test
    void testUpdateShelf() {
        Shelf newShelf = SS.create(new Shelf(100, 70.0));
        newShelf.setCapacity(200);
        SS.update(newShelf);

        Shelf updatedShelf = SS.findById(newShelf.getId());
        assertNotNull(updatedShelf);
        assertEquals(200, updatedShelf.getCapacity());
    }

    @Test
    void testDeleteShelf() {
        Shelf newShelf = SS.create(new Shelf(400, 90.0));
        boolean isDeleted = SS.delete(newShelf.getId());

        assertTrue(isDeleted);
        assertNull(SS.findById(newShelf.getId()));
    }

    @Test
    void testFindAllShelves() {
        SS.create(new Shelf(500, 20.0));
        SS.create(new Shelf(600, 25.0));

        assertEquals(2, SS.getAllEntities().size());
    }

    /*
     * ## Fail Tests ##
     */
    @Test
    void testFindNonExistentShelfReturnsNull() {
        Long id = 50L;
        assertNull(SS.findById(id));
    }

    @Test
    void testDeleteNonExistentShelfReturnsFalse() {
        boolean isDeleted = SS.delete(50L);
        assertFalse(isDeleted);
    }

    @Test
    void testUpdateShelfThatDoesNotExistThrowsException() {
        Shelf shelf = new Shelf(10, 5.0);
        shelf.setId(50L); // ID inexistente

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> SS.update(shelf));

        assertTrue(exception.getMessage().contains("Shelf not found"));
    }
}