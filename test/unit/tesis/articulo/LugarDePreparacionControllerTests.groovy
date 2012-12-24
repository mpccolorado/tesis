
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(LugarDePreparacionController)
@Mock(LugarDePreparacion)
class LugarDePreparacionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lugarDePreparacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lugarDePreparacionInstanceList.size() == 0
        assert model.lugarDePreparacionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.lugarDePreparacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.lugarDePreparacionInstance != null
        assert view == '/lugarDePreparacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lugarDePreparacion/show/1'
        assert controller.flash.message != null
        assert LugarDePreparacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lugarDePreparacion/list'


        populateValidParams(params)
        def lugarDePreparacion = new LugarDePreparacion(params)

        assert lugarDePreparacion.save() != null

        params.id = lugarDePreparacion.id

        def model = controller.show()

        assert model.lugarDePreparacionInstance == lugarDePreparacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lugarDePreparacion/list'


        populateValidParams(params)
        def lugarDePreparacion = new LugarDePreparacion(params)

        assert lugarDePreparacion.save() != null

        params.id = lugarDePreparacion.id

        def model = controller.edit()

        assert model.lugarDePreparacionInstance == lugarDePreparacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lugarDePreparacion/list'

        response.reset()


        populateValidParams(params)
        def lugarDePreparacion = new LugarDePreparacion(params)

        assert lugarDePreparacion.save() != null

        // test invalid parameters in update
        params.id = lugarDePreparacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lugarDePreparacion/edit"
        assert model.lugarDePreparacionInstance != null

        lugarDePreparacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lugarDePreparacion/show/$lugarDePreparacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lugarDePreparacion.clearErrors()

        populateValidParams(params)
        params.id = lugarDePreparacion.id
        params.version = -1
        controller.update()

        assert view == "/lugarDePreparacion/edit"
        assert model.lugarDePreparacionInstance != null
        assert model.lugarDePreparacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lugarDePreparacion/list'

        response.reset()

        populateValidParams(params)
        def lugarDePreparacion = new LugarDePreparacion(params)

        assert lugarDePreparacion.save() != null
        assert LugarDePreparacion.count() == 1

        params.id = lugarDePreparacion.id

        controller.delete()

        assert LugarDePreparacion.count() == 0
        assert LugarDePreparacion.get(lugarDePreparacion.id) == null
        assert response.redirectedUrl == '/lugarDePreparacion/list'
    }
}
