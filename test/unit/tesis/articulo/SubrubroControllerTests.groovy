
package tesis.articulo



import org.junit.*
import grails.test.mixin.*

@TestFor(SubrubroController)
@Mock(Subrubro)
class SubrubroControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/subrubro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.subrubroInstanceList.size() == 0
        assert model.subrubroInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.subrubroInstance != null
    }

    void testSave() {
        controller.save()

        assert model.subrubroInstance != null
        assert view == '/subrubro/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/subrubro/show/1'
        assert controller.flash.message != null
        assert Subrubro.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/subrubro/list'


        populateValidParams(params)
        def subrubro = new Subrubro(params)

        assert subrubro.save() != null

        params.id = subrubro.id

        def model = controller.show()

        assert model.subrubroInstance == subrubro
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/subrubro/list'


        populateValidParams(params)
        def subrubro = new Subrubro(params)

        assert subrubro.save() != null

        params.id = subrubro.id

        def model = controller.edit()

        assert model.subrubroInstance == subrubro
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/subrubro/list'

        response.reset()


        populateValidParams(params)
        def subrubro = new Subrubro(params)

        assert subrubro.save() != null

        // test invalid parameters in update
        params.id = subrubro.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/subrubro/edit"
        assert model.subrubroInstance != null

        subrubro.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/subrubro/show/$subrubro.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        subrubro.clearErrors()

        populateValidParams(params)
        params.id = subrubro.id
        params.version = -1
        controller.update()

        assert view == "/subrubro/edit"
        assert model.subrubroInstance != null
        assert model.subrubroInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/subrubro/list'

        response.reset()

        populateValidParams(params)
        def subrubro = new Subrubro(params)

        assert subrubro.save() != null
        assert Subrubro.count() == 1

        params.id = subrubro.id

        controller.delete()

        assert Subrubro.count() == 0
        assert Subrubro.get(subrubro.id) == null
        assert response.redirectedUrl == '/subrubro/list'
    }
}
