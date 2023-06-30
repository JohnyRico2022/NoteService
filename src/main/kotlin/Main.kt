data class Note(
    var title: String, //Заголовок заметки.
    var text: String, //Текст заметки.
    var noteId: Int = 0, //Идентификатор заметки.
    val userId: Int, //Идентификатор пользователя.
    var comment: MutableList<Comment> = mutableListOf()// Комментарии к заметке
)

data class Comment(
    var message: String = "", //Текст комментария.
    var commentId: Int = 0, //Идентификатор комментария.
    var deletedComment: Boolean = false//Метка удаления комментария (true - комментарий удален)
)

object NoteList {

    private var noteCurrentId: Int = 0
    private var commentCurrenttId: Int = 0
    private var notesList = mutableListOf<Note>()

    fun print() {
        println(notesList)
    }

    fun add(note: Note): Note {  //Создает новую заметку у текущего пользователя.

        notesList += note
        noteCurrentId++
        note.noteId = noteCurrentId
        return notesList.last()
    }


    fun delete(note: Note): Boolean {  //Удаляет заметку текущего пользователя.

        notesList.remove(note)
        return notesList.contains(note)
    }

    fun edit(noteId: Int, title: String, text: String): Boolean { //Редактирует заметку текущего пользователя.

        for ((index: Int, noteNew: Note) in notesList.withIndex()) {
            if (noteNew.noteId == noteId) {
                notesList[index] = noteNew.copy(title = title, text = text)
                return true
            }
        }
        return false
    }

    fun get():MutableList<Note>? {  //Возвращает список заметок, созданных пользователем.

        if (notesList.size > 0){
            return     notesList
        }
        return null
    }

    fun getById(noteId: Int): Note? {   //Возвращает заметку по её id.

        for (newNote in notesList)
            if (newNote.noteId == noteId) {
                return newNote
            }
        return null
    }

    fun createComment(note: Note, message: String): Comment {  //Добавляет новый комментарий к заметке.

        commentCurrenttId++
        note.comment.add(Comment(message, commentCurrenttId, false))
        return note.comment.last()
    }

    fun getComments(note: Note): MutableList<Comment> { //Возвращает список комментариев к заметке.

        val commentList = note.comment
        var listOnlyFalse = emptyList<Comment>().toMutableList()

        for (comment in commentList)
            if (!comment.deletedComment) {
                listOnlyFalse += comment
            }
        return listOnlyFalse
    }

    fun editComment(note: Note, commentId: Int, newMessage: String): Boolean { //Редактирует указанный комментарий у заметки.

        val list = note.comment
        val index = note.comment.indexOfFirst { it.commentId == commentId }
        return if (index >= 0) {
            list[index] = Comment(newMessage, commentId, false)
            true
        } else {
            false
        }
    }

    fun deleteComment(note: Note, commentId: Int): Boolean {  //Удаляет комментарий к заметке.

        val list = note.comment
        val index = note.comment.indexOfFirst { it.commentId == commentId }
        val mes = list[index].message

        return if (index >= 0) {
            list[index] = Comment().copy(message = mes, commentId = commentId, deletedComment = true)

            true
        } else {
            false
        }
    }

    fun restoreComment(note: Note, commentId: Int): Boolean { //Восстанавливает удалённый комментарий.

        val list = note.comment
        val index = note.comment.indexOfFirst { it.commentId == commentId }
        val mes = list[index].message

        return if (index >= 0) {
            list[index] = Comment().copy(message = mes, commentId = commentId, deletedComment = false)
            true
        } else {
            false
        }
    }

    fun clear() {
        noteCurrentId = 0
        commentCurrenttId = 0
        notesList = emptyList<Note>().toMutableList()
    }
}

fun main() {

    val service = NoteList
    val note1 = Note("Title_1", "Text_1", userId = 55)
    val note2 = Note("Title_2", "Text_2", userId = 55)
    val note3 = Note("Title_3", "Text_3", userId = 55)


    service.add(note1)
    service.add(note2)
    service.add(note3)
    service.createComment(note1,"comment_1")
    service.createComment(note1,"comment_2")

    service.print()

}

