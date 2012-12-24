
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(UnidadDeMedidaController)
@Mock(UnidadDeMedida)
class UnidadDeMedidaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/unidadDeMedida/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.unidadDeMedidaInstanceList.size() == 0
        assert model.unidadDeMedidaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.unidadDeMedidaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.unidadDeMedidaInstance != null
        assert view == '/unidadDeMedida/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/unidadDeMedida/show/1'
        assert controller.flash.message != null
        assert UnidadDeMedida.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadDeMedida/list'


        populateValidParams(params)
        def unidadDeMedida = new UnidadDeMedida(params)

        assert unidadDeMedida.save() != null

        params.id = unidadDeMedida.id

        def model = controller.show()

        assert model.unidadDeMedidaInstance == unidadDeMedida
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadDeMedida/list'


        populateValidParams(params)
        def unidadDeMedida = new UnidadDeMedida(params)

        assert unidadDeMedida.save() != null

        params.id = unidadDeMedida.id

        def model = controller.edit()

        assert model.unidadDeMedidaInstance == unidadDeMedida
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadDeMedida/list'

        response.reset()


        populateValidParams(params)
        def unidadDeMedida = new UnidadDeMedida(params)

        assert unidadDeMedida.save() != null

        // test invalid parameters in update
        params.id = unidadDeMedida.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/unidadDeMedida/edit"
        assert model.unidadDeMedidaInstance != null

        unidadDeMedida.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/unidadDeMedida/show/$unidadDeMedida.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        unidadDeMedida.clearErrors()

        populateValidParams(params)
        params.id = unidadDeMedida.id
        params.version = -1
        controller.update()

        assert view == "/unidadDeMedida/edit"
        assert model.unidadDeMedidaInstance != null
        assert model.unidadDeMedidaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/unidadDeMedida/list'

        response.reset()

        populateValidParams(params)
        def unidadDeMedida = new UnidadDeMedida(params)

        assert unidadDeMedida.save() != null
        assert UnidadDeMedida.count() == 1

        params.id = unidadDeMedida.id

        controller.delete()

        assert UnidadDeMedida.count() == 0
        assert UnidadDeMedida.get(unidadDeMedida.id) == null
        assert response.redirectedUrl == '/unidadDeMedida/list'
    }
}
