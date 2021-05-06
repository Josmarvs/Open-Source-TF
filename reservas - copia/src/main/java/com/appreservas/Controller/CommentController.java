package com.appreservas.Controller;

import com.appreservas.Entity.Comment;
import com.appreservas.Entity.Beginner;
import com.appreservas.Entity.Chef;
import com.appreservas.Service.ICommentService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Api(tags = "Comment", value = "Service Web RESTFul de Comments")
public class CommentController {

    @Autowired
    private IBeginnerService beginnerService;

    @Autowired
    private ICommentService commentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Comments",notes = "Método para listar todos los Comments")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Comments encontrados"),
            @ApiResponse(code = 404,message = "Comments no encontrados")
    })
    public ResponseEntity<List<Comment>>findAllComment(){
        try {
            List<Comment> comments = commentService.getAll();
            if(comments.size()>0)
                return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
            else
                return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Comment por Id",notes = "Método para listar un Comment por Id")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Comment encontrados"),
            @ApiResponse(code = 404,message = "Comment no encontrados")
    })
    public ResponseEntity<Comment>findCommentById(@PathVariable("id")Long id){
        try{
            Optional<Comment>comment=commentService.getById(id);
            if(!comment.isPresent())
                return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Comment>(comment.get(),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/searchByDate",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Comment entre fechas",notes = "Método para listar un Comment entre fechas")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Comment encontrados"),
            @ApiResponse(code = 404,message = "Comment no encontrados")
    })
    public ResponseEntity<List<Comment>> findCommentByDate(@RequestParam("publication_Date") String publication_string){
        try {
            Date publication_Date=ParseDate(publication_string);
            List<Comment> comments =commentService.find(publication_Date);
            if (comments.size()>0)
                return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
            else
                return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception e){

        }
        return result;
    }

    @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Comment", notes = "Método que registra un Comment")
    @ApiResponses({
            @ApiResponse(code=201,message = "Comment creado"),
            @ApiResponse(code=404, message = "Comment no creado")
    })
    public ResponseEntity<Comment> insertCommentBeginner(@PathVariable("id")Long id, @Valid @RequestBody Comment comment){
        try {
            Optional<Beginner>beginner=beginnerService.getById(id);
            if(beginner.isPresent()){
                comment.setBeginner(beginner.get());
                Comment commentNew =commentService.save(comment);
                return ResponseEntity.status(HttpStatus.CREATED).body(commentNew);
            }
            else
                return new ResponseEntity<Comment>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception e){
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Comment", notes = "Método que registra un Comment")
    @ApiResponses({
            @ApiResponse(code=201,message = "Comment creado"),
            @ApiResponse(code=404, message = "Comment no creado")
    })
    public ResponseEntity<Comment> insertCommentChef(@PathVariable("id")Long id, @Valid @RequestBody Comment comment){
        try {
            Optional<Chef>chef=chefService.getById(id);
            if(chef.isPresent()){
                comment.setChef(chef.get());
                Comment commentNew =commentService.save(comment);
                return ResponseEntity.status(HttpStatus.CREATED).body(commentNew);
            }
            else
                return new ResponseEntity<Comment>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception e){
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Comment",notes = "Metodo que actualiza los datos de Comment")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Comment actualizados"),
            @ApiResponse(code=404,message = "Comment no encontrado")
    })
    public ResponseEntity<Comment> updateComment(@PathVariable("id")Long id, @Valid @RequestBody Comment comment){
        try {
            Optional<Comment>commentOld=commentService.getById(id);
            if(!commentOld.isPresent())
                return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
            else{
                comment.setId(id);
                commentService.save(comment);
                return new ResponseEntity<Comment>(comment,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Comment por Id",notes = "Metodo que elimina los datos de un Comment")
    @ApiResponses({
            @ApiResponse(code=201, message = "Datos de Comment eliminados"),
            @ApiResponse(code=404,message = "Comment no encontrado")
    })
    public ResponseEntity<Comment> deleteComment(@PathVariable("id")Long id){
        try {
            Optional<Comment> commentDelete=commentService.getById(id);
            if(commentDelete.isPresent()){
                commentService.delete(id);
                return new ResponseEntity<Comment>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
