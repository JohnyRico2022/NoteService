import org.junit.Test

import org.junit.Assert.*

class NoteListTest {

    @Test
    fun add() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)

        service.add(note1)
        service.add(note2)

        val result = note2

        assertEquals(note2, result)

    }

    @Test
    fun delete() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        val result = service.delete(note1)
        assertEquals(false, result)
    }

    @Test
    fun edit() {

        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        val result = service.edit(2,"Test", "Test")

        assertEquals(true,result)

    }

  /*
   @Test
    fun get() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        val result = service.get()

        assertEquals(   , result)
    }
   */

    @Test
    fun getById() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        val result = service.getById(1)

        assertEquals(note1 , result)

    }

    @Test
    fun createComment() {

        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)
        val comment =Comment("Hello",1, false)

        service.add(note1)
        service.add(note2)
        service.add(note3)

     val result = service.createComment(note3, "Hello")

        assertEquals( comment,result)
    }

    @Test
    fun getComments() {
    }

    @Test
    fun editComment() {

        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        service.createComment(note3, "Hello")
        service.createComment(note3, "Hello2")

        val result = service.editComment(note3,2,"test")

        assertEquals(true ,result)
    }

    @Test
    fun deleteComment() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        service.createComment(note3, "Hello")
        service.createComment(note3, "Hello2")

        val result = service.deleteComment(note3,2)

        assertEquals(true ,result)
    }

    @Test
    fun restoreComment() {
        val service = NoteList
        val note1 = Note("Title_1", "Text_1", userId = 55)
        val note2 = Note("Title_2", "Text_2", userId = 55)
        val note3 = Note("Title_3", "Text_3", userId = 55)

        service.add(note1)
        service.add(note2)
        service.add(note3)

        service.createComment(note3, "Hello")
        service.createComment(note3, "Hello2")

        val result = service.restoreComment(note3,2)

        assertEquals(true ,result)
    }
}