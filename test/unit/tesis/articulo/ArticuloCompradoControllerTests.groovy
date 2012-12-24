
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(ArticuloCompradoController)
@Mock(ArticuloComprado)
class ArticuloCompradoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/articuloComprado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.articuloCompradoInstanceList.size() == 0
        assert model.articuloCompradoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.articuloCompradoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.articuloCompradoInstance != null
        assert view == '/articuloComprado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/articuloComprado/show/1'
        assert controller.flash.message != null
        assert ArticuloComprado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloComprado/list'


        populateValidParams(params)
        def articuloComprado = new ArticuloComprado(params)

        assert articuloComprado.save() != null

        params.id = articuloComprado.id

        def model = controller.show()

        assert model.articuloCompradoInstance == articuloComprado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloComprado/list'


        populateValidParams(params)
        def articuloComprado = new ArticuloComprado(params)

        assert articuloComprado.save() != null

        params.id = articuloComprado.id

        def model = controller.edit()

        assert model.articuloCompradoInstance == articuloComprado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/articuloComprado/list'

        response.reset()


        populateValidParams(params)
        def articuloComprado = new ArticuloComprado(params)

        assert articuloComprado.save() != null

        // test invalid parameters in update
        params.id = articuloComprado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/articuloComprado/edit"
        assert model.articuloCompradoInstance != null

        articuloComprado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/articuloComprado/show/$articuloComprado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        articuloComprado.clearErrors()

        populateValidParams(params)
        params.id = articuloComprado.id
        params.version = -1
        controller.update()

        assert view == "/articuloComprado/edit"
        assert model.articuloCompradoInstance != null
        assert model.articuloCompradoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/articuloComprado/list'

        response.reset()

        populateValidParams(params)
        def articuloComprado = new ArticuloComprado(params)

        assert articuloComprado.save() != null
        assert ArticuloComprado.count() == 1

        params.id = articuloComprado.id

        controller.delete()

        assert ArticuloComprado.count() == 0
        assert ArticuloComprado.get(articuloComprado.id) == null
        assert response.redirectedUrl == '/articuloComprado/list'
    }
}
