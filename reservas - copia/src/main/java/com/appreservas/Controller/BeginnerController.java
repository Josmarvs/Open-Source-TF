package com.appreservas.Controller;


import com.appreservas.Entity.Beginner;
import com.appreservas.Service.IBeginnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@Api(tags = "Customer", value = "Service Web RESTFul de Customers")
public class BeginnerController {

    @Autowired
    private IBeginnerService beginnerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Customers", notes = "Métodos para listr todos todos los customers")
    @ApiResponses({
            @ApiResponse(code=201,message = "Customers encontrados"),
            @ApiResponse(code = 404,message = "Customers no encontrados")
    })
    public ResponseEntity<List<Beginner>>findAll(){
        try{
            List<Beginner> beginners =beginnerService.getAll();
            if(beginners.size()>0)
                return new ResponseEntity<List<Beginner>>(beginners,HttpStatus.OK);
            else
                return new ResponseEntity<List<Beginner>>(HttpStatus.NOT_FOUND);

        }catch(Exception ex){
            return new ResponseEntity<List<Beginner>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Beginner por Id", notes = "Métodos para encontrar un Beginner por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201,message = "Beginner encontrado"),
            @ApiResponse(code=404,message = "Beginner no encontrado")
    })
    public ResponseEntity<Beginner> findById(@PathVariable("id") Long id){
        try {
           Optional<Beginner> beginner=beginnerService.getById(id);
           if (!beginner.isPresent())
               return new ResponseEntity<Beginner>(HttpStatus.NOT_FOUND);
           return new ResponseEntity<Beginner>(beginner.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Beginner>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "searchByDni/{dni}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Beginner por DNI", notes = "Métodos para encontrar un Beginner por su respectivo DNI")
    @ApiResponses({
            @ApiResponse(code=201,message = "Customer encontrado"),
            @ApiResponse(code=404,message = "Customer no encontrado")
    })
    public ResponseEntity<Beginner> findByDni(@PathVariable("dni")String dni){
        try{
            Beginner beginner =beginnerService.findByDni(dni);
            if(beginner ==null)
                return new ResponseEntity<Beginner>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Beginner>(beginner,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Beginner>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("searchByLastname/{lastname}")
    @ApiOperation(value = "Buscar Beginner por lastname", notes = "Métodos para encontrar un Beginner por su respectivo lastname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Beginner encontrados"),
            @ApiResponse(code=404,message = "Beginner no encontrados")
    })
    public ResponseEntity<List<Beginner>> findByLastName(@PathVariable("lastname")String lastname){
        try {
            List<Beginner> beginners =beginnerService.findByLastname(lastname);
            if(beginners.size()>0)
                return new ResponseEntity<List<Beginner>>(beginners,HttpStatus.OK);
            else
                return new ResponseEntity<List<Beginner>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Beginner>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("searchByFirstname/{firstname}")
    @ApiOperation(value = "Buscar Beginner por firstname", notes = "Métodos para encontrar un Beginner por su respectivo firstname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Beginner encontrados"),
            @ApiResponse(code=404,message = "Beginner no encontrados")
    })
    public ResponseEntity<List<Beginner>> findByFirstName(@PathVariable("firstname")String firstname){
        try {
            List<Beginner> beginners =beginnerService.findByFirstname(firstname);
            if(beginners.size()>0)
                return new ResponseEntity<List<Beginner>>(beginners,HttpStatus.OK);
            else
                return new ResponseEntity<List<Beginner>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Beginner>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "searchByFirstnameAndLastname/{firstname}/{lastname}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Beginner por firstname y lastname", notes = "Métodos para encontrar un Beginner por su respectivo firstname y lastname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Beginner encontrados"),
            @ApiResponse(code=404,message = "Beginner no encontrados")
    })
    public ResponseEntity<List<Beginner>> findByFirstnameAndLastname(
            @PathVariable("firstname")String firstname,@PathVariable("lastname")String lastname
    ){
        try {
            List<Beginner> beginners =beginnerService.findByFirstnameAndLastname(firstname,lastname);
            if(beginners.size()>0)
                return new ResponseEntity<List<Beginner>>(beginners,HttpStatus.OK);
            else
                return new ResponseEntity<List<Beginner>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Beginner>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Beginner", notes = "Método que registra Beginners en BD")
    @ApiResponses({
            @ApiResponse(code=201,message = "Beginner creado"),
            @ApiResponse(code=404, message = "Beginner no creado")
    })
    public ResponseEntity<Beginner> insertCustomer(@Valid @RequestBody Beginner beginner){
        try {
            Beginner beginnerNew =beginnerService.save(beginner);
            return ResponseEntity.status(HttpStatus.CREATED).body(beginnerNew);
        }catch (Exception e){
            return new ResponseEntity<Beginner>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Beginners",notes = "Metodo que actualiza los datos de Beginners")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Beginner actualizados"),
            @ApiResponse(code=404,message = "Beginner no encontrado")
    })
    public ResponseEntity<Beginner>updateCustomer(
            @PathVariable("id") Long id, @Valid @RequestBody Beginner beginner){
        try {
            Optional<Beginner>customerUp=beginnerService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<Beginner>(HttpStatus.NOT_FOUND);
            beginner.setId(id);
            beginnerService.save(beginner);
            return new ResponseEntity<Beginner>(beginner,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Beginner>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Beginners",notes = "Metodo que elimina los datos de Beginners")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Beginner eliminados"),
            @ApiResponse(code=404,message = "Beginner no encontrado")
    })
    public ResponseEntity<Beginner>deleteCustomer(@PathVariable("id")Long id){
        try {
            Optional<Beginner>customerDelete=beginnerService.getById(id);
            if(!customerDelete.isPresent())
                return new ResponseEntity<Beginner>(HttpStatus.NOT_FOUND);
            beginnerService.delete(id);
            return new ResponseEntity<Beginner>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Beginner>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
