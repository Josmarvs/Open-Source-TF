package com.appreservas.Controller;


import com.appreservas.Entity.Chef;
import com.appreservas.Service.IChefService;
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
@RequestMapping("/api/chefs")
@Api(tags = "Chef", value = "Service Web RESTFul de Chefs")
public class ChefController {

    @Autowired
    private IChefService chefService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Chefs", notes = "Métodos para listr todos todos los chefs")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chefs encontrados"),
            @ApiResponse(code = 404,message = "Chefs no encontrados")
    })
    public ResponseEntity<List<Chef>>findAll(){
        try{
            List<Chef> chefs =chefService.getAll();
            if(chefs.size()>0)
                return new ResponseEntity<List<Chef>>(chefs,HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        }catch(Exception ex){
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por Id", notes = "Métodos para encontrar un chef por su respectivo Id")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef encontrado"),
            @ApiResponse(code=404,message = "Chef no encontrado")
    })
    public ResponseEntity<Chef> findById(@PathVariable("id") Long id){
        try {
            Optional<Chef> chef=chefService.getById(id);
            if (!chef.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Chef>(chef.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "searchByDni/{dni}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por DNI", notes = "Métodos para encontrar un chef por su respectivo DNI")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef encontrado"),
            @ApiResponse(code=404,message = "Chef no encontrado")
    })
    public ResponseEntity<Chef> findByDni(@PathVariable("dni")String dni){
        try{
            Chef chef=chefService.findByDni(dni);
            if(chef==null)
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Chef>(chef,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("searchByLastname/{lastname}")
    @ApiOperation(value = "Buscar Chef por lastname", notes = "Métodos para encontrar un chef por su respectivo lastname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef encontrados"),
            @ApiResponse(code=404,message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByLastName(@PathVariable("lastname")String lastname){
        try {
            List<Chef>chefs=chefService.findByLastname(lastname);
            if(chefs.size()>0)
                return new ResponseEntity<List<Chef>>(chefs,HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("searchByFirstname/{firstname}")
    @ApiOperation(value = "Buscar Chef por firstname", notes = "Métodos para encontrar un chef por su respectivo firstname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef encontrados"),
            @ApiResponse(code=404,message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByFirstName(@PathVariable("firstname")String firstname){
        try {
            List<Chef>chefs=chefService.findByFirstname(firstname);
            if(chefs.size()>0)
                return new ResponseEntity<List<Chef>>(chefs,HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "searchByFirstnameAndLastname/{firstname}/{lastname}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Chef por firstname y lastname", notes = "Métodos para encontrar un chef por su respectivo firstname y lastname")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef encontrados"),
            @ApiResponse(code=404,message = "Chef no encontrados")
    })
    public ResponseEntity<List<Chef>> findByFirstnameAndLastname(
            @PathVariable("firstname")String firstname,@PathVariable("lastname")String lastname
    ){
        try {
            List<Chef>chefs =chefService.findByFirstnameAndLastname(firstname,lastname);
            if(chefs.size()>0)
                return new ResponseEntity<List<Chef>>(chefs,HttpStatus.OK);
            else
                return new ResponseEntity<List<Chef>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Chef>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de Chefs", notes = "Método que registra chefs en BD")
    @ApiResponses({
            @ApiResponse(code=201,message = "Chef creado"),
            @ApiResponse(code=404, message = "Chef no creado")
    })
    public ResponseEntity<Chef> insertChef(@Valid @RequestBody Chef chef){
        try {
            Chef chefNew=chefService.save(chef);
            return ResponseEntity.status(HttpStatus.CREATED).body(chefNew);
        }catch (Exception e){
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de chefs",notes = "Metodo que actualiza los datos de Chefs")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Chef actualizados"),
            @ApiResponse(code=404,message = "Chef no encontrado")
    })
    public ResponseEntity<Chef>updateChef(
            @PathVariable("id") Long id, @Valid @RequestBody Chef chef){
        try {
            Optional<Chef>chefUp=chefService.getById(id);
            if(!chefUp.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            chef.setId(id);
            chefService.save(chef);
            return new ResponseEntity<Chef>(chef,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de chefs",notes = "Metodo que elimina los datos de Chefs")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de Chef eliminados"),
            @ApiResponse(code=404,message = "Chef no encontrado")
    })
    public ResponseEntity<Chef>deleteChef(@PathVariable("id")Long id){
        try {
            Optional<Chef>chefDelete=chefService.getById(id);
            if(!chefDelete.isPresent())
                return new ResponseEntity<Chef>(HttpStatus.NOT_FOUND);
            chefService.delete(id);
            return new ResponseEntity<Chef>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Chef>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
