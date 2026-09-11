package org.uv.dao;

import org.uv.model.Reporte;
import org.uv.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    public List<Reporte> listar() throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        String sql = "SELECT id, usuario_id, categoria_id, estado_id, titulo, descripcion, ubicacion, fecha_creacion, prioridad FROM reportes ORDER BY id";

        try (Connection con = DriverManager.getConnection(DatabaseConfig.getUrl(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setId(rs.getInt("id"));
                reporte.setUsuarioId(rs.getInt("usuario_id"));
                reporte.setCategoriaId(rs.getInt("categoria_id"));
                reporte.setEstadoId(rs.getInt("estado_id"));
                reporte.setTitulo(rs.getString("titulo"));
                reporte.setDescripcion(rs.getString("descripcion"));
                reporte.setUbicacion(rs.getString("ubicacion"));
                Timestamp timestamp = rs.getTimestamp("fecha_creacion");
                if (timestamp != null) {
                    reporte.setFechaCreacion(timestamp.toLocalDateTime());
                }
                reporte.setPrioridad(rs.getString("prioridad"));
                reportes.add(reporte);
            }
        }
        return reportes;
    }

    public void guardar(Reporte reporte) throws SQLException {
        String sql = "INSERT INTO reportes (usuario_id, categoria_id, estado_id, titulo, descripcion, ubicacion, prioridad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DatabaseConfig.getUrl(), DatabaseConfig.getUser(), DatabaseConfig.getPassword());
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, reporte.getUsuarioId() != null ? reporte.getUsuarioId() : 1);
            ps.setInt(2, reporte.getCategoriaId() != null ? reporte.getCategoriaId() : 1);
            ps.setInt(3, reporte.getEstadoId() != null ? reporte.getEstadoId() : 1);
            ps.setString(4, reporte.getTitulo());
            ps.setString(5, reporte.getDescripcion());
            ps.setString(6, reporte.getUbicacion());
            ps.setString(7, reporte.getPrioridad());
            ps.executeUpdate();
        }
    }
}
