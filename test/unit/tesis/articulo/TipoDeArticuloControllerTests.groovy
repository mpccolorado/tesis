
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoDeArticuloController)
@Mock(TipoDeArticulo)
class TipoDeArticuloControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoDeArticulo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoDeArticuloInstanceList.size() == 0
        assert model.tipoDeArticuloInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tipoDeArticuloInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoDeArticuloInstance != null
        assert view == '/tipoDeArticulo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoDeArticulo/show/1'
        assert controller.flash.message != null
        assert TipoDeArticulo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDeArticulo/list'


        populateValidParams(params)
        def tipoDeArticulo = new TipoDeArticulo(params)

        assert tipoDeArticulo.save() != null

        params.id = tipoDeArticulo.id

        def model = controller.show()

        assert model.tipoDeArticuloInstance == tipoDeArticulo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDeArticulo/list'


        populateValidParams(params)
        def tipoDeArticulo = new TipoDeArticulo(params)

        assert tipoDeArticulo.save() != null

        params.id = tipoDeArticulo.id

        def model = controller.edit()

        assert model.tipoDeArticuloInstance == tipoDeArticulo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDeArticulo/list'

        response.reset()


        populateValidParams(params)
        def tipoDeArticulo = new TipoDeArticulo(params)

        assert tipoDeArticulo.save() != null

        // test invalid parameters in update
        params.id = tipoDeArticulo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoDeArticulo/edit"
        assert model.tipoDeArticuloInstance != null

        tipoDeArticulo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoDeArticulo/show/$tipoDeArticulo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoDeArticulo.clearErrors()

        populateValidParams(params)
        params.id = tipoDeArticulo.id
        params.version = -1
        controller.update()

        assert view == "/tipoDeArticulo/edit"
        assert model.tipoDeArticuloInstance != null
        assert model.tipoDeArticuloInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoDeArticulo/list'

        response.reset()

        populateValidParams(params)
        def tipoDeArticulo = new TipoDeArticulo(params)

        assert tipoDeArticulo.save() != null
        assert TipoDeArticulo.count() == 1

        params.id = tipoDeArticulo.id

        controller.delete()

        assert TipoDeArticulo.count() == 0
        assert TipoDeArticulo.get(tipoDeArticulo.id) == null
        assert response.redirectedUrl == '/tipoDeArticulo/list'
    }
}
