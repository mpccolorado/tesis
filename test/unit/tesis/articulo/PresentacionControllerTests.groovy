
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(PresentacionController)
@Mock(Presentacion)
class PresentacionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/presentacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.presentacionInstanceList.size() == 0
        assert model.presentacionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.presentacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.presentacionInstance != null
        assert view == '/presentacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/presentacion/show/1'
        assert controller.flash.message != null
        assert Presentacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/presentacion/list'


        populateValidParams(params)
        def presentacion = new Presentacion(params)

        assert presentacion.save() != null

        params.id = presentacion.id

        def model = controller.show()

        assert model.presentacionInstance == presentacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/presentacion/list'


        populateValidParams(params)
        def presentacion = new Presentacion(params)

        assert presentacion.save() != null

        params.id = presentacion.id

        def model = controller.edit()

        assert model.presentacionInstance == presentacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/presentacion/list'

        response.reset()


        populateValidParams(params)
        def presentacion = new Presentacion(params)

        assert presentacion.save() != null

        // test invalid parameters in update
        params.id = presentacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/presentacion/edit"
        assert model.presentacionInstance != null

        presentacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/presentacion/show/$presentacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        presentacion.clearErrors()

        populateValidParams(params)
        params.id = presentacion.id
        params.version = -1
        controller.update()

        assert view == "/presentacion/edit"
        assert model.presentacionInstance != null
        assert model.presentacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/presentacion/list'

        response.reset()

        populateValidParams(params)
        def presentacion = new Presentacion(params)

        assert presentacion.save() != null
        assert Presentacion.count() == 1

        params.id = presentacion.id

        controller.delete()

        assert Presentacion.count() == 0
        assert Presentacion.get(presentacion.id) == null
        assert response.redirectedUrl == '/presentacion/list'
    }
}
