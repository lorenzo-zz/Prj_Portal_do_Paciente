package com.example.oracleapi.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.RowId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.oracleapi.DTO.CpfDTO;
import com.example.oracleapi.DTO.RequisicaoExameDTO;
import com.example.oracleapi.DTO.ResultadoExameDTO;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Entity.RequisicaoExame;
import com.example.oracleapi.Repository.ExameRepository;
import com.example.oracleapi.Repository.PacienteRepository;

@Service
public class ExameService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ExameRepository exameRepository;

    public void cadastrarResultadoExame(ResultadoExameDTO resultadoExameDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_resultado_exame(?, ?)}");) {

            stmt.setString(1, String.valueOf(Date.valueOf(resultadoExameDTO.descricao())));
            stmt.setRowId(2, (RowId) resultadoExameDTO.requisicaoExame());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarRequisicaoExame(RequisicaoExameDTO requisicaoExameDTO, MultipartFile arquivo)
            throws SQLException {

        int paciente = pacienteRepository.findByCpf(requisicaoExameDTO.pacienteCpf())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada")).getId();

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_requisicao_exame(?,?,?,?,?)");

            String nomeArquivo = null;
            if (arquivo != null && !arquivo.isEmpty()) {
                nomeArquivo = requisicaoExameDTO.pacienteCpf() + "_" + arquivo.getOriginalFilename();
            }

            stmt.setString(1, requisicaoExameDTO.tipoExame());
            stmt.setString(2, requisicaoExameDTO.observacoes());
            stmt.setString(3, requisicaoExameDTO.tipoConvenio());
            stmt.setString(4, nomeArquivo);
            stmt.setInt(5, paciente);

            stmt.execute();

            if (arquivo != null && !arquivo.isEmpty()) {
                salvarDocumento(requisicaoExameDTO.pacienteCpf(), arquivo);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao processar a requisição do exame: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o documento do paciente: " + e.getMessage(), e);
        }
    }

    public void salvarDocumento(String cpf, MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty())
            return;

        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String nomeArquivo = cpf + "_" + arquivo.getOriginalFilename();
        Path caminhoArquivo = uploadPath.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IOException("Paciente não encontrado"));

        paciente.setDocumento(nomeArquivo);
        pacienteRepository.save(paciente);
    }

    public List<RequisicaoExame> listarExames(CpfDTO cpfPaciente) {
        return exameRepository.findAll()
        .stream()
        .filter(e -> e.getPaciente().getCpf().equals(cpfPaciente.cpf()))
        .toList();
    }
}
